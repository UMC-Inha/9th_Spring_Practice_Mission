package umc.domain.review.service.query;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.domain.region.entity.QRegion;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.entity.QReview;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

        private final ReviewRepository reviewRepository;
        private final StoreRepository storeRepository;

        //워크북 실습 코드
        @Override
        public List<ReviewResDTO.ReviewPreviewWorkbookDTO> searchReview(String query, String type) {

            //Q클래스 정의
            QReview review = QReview.review;
            QRegion region = QRegion.region;

            //BooleanBuilder 정의
            BooleanBuilder builder = new BooleanBuilder();

            //BooleanBuilder 사용

            //동적 쿼리: 검색 조건
            if (type.equals("location")) {
                builder.and(region.name.contains(query));
            }
            if (type.equals("star")) {
                builder.and(review.rating.goe(Float.parseFloat(query)));
            }
            if (type.equals("both")) {

                //&기준 변환
                String firstQuery = query.split("&")[0];
                String secondQuery = query.split("&")[1];

                //동적 쿼리
                builder.and(region.name.contains(firstQuery));
                builder.and(review.rating.goe(Float.parseFloat(secondQuery)));
            }


            List<Review> reviews = reviewRepository.searchReview(builder);
            return reviews.stream()
                    .map(ReviewConverter::toReviewPreviewBookDTO)
                    .collect(Collectors.toList());
        }

        //과제(미션) 코드
        public List<ReviewResDTO.ReviewPreviewWorkbookDTO> getMyReviews(Long userId, Long storeId, Float starFloor) {

            //리포지토리에 파라미터 그대루 넘기기
            List<Review>reviews = reviewRepository.findMyReviewsFiltered(userId, storeId, starFloor);

            //Entity List를 Dto List로 변환
            return reviews.stream()
                    .map(ReviewConverter::toReviewPreviewBookDTO)
                    .collect(Collectors.toList());
        }

        @Override
        public ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page

        ){
            //가게를 가져온다 (가게 존재 여부 검증)
            Store store = storeRepository.findByName(storeName).orElseThrow(()-> new StoreException(StoreErrorCode.NOT_FOUND));

            //가게에 맞는 리뷰를 가져온다 (Offset 페이징)
            PageRequest pageRequest = PageRequest.of(page,5);
            Page<Review> result = reviewRepository.findAllByStore(store,pageRequest);

            return ReviewConverter.toReviewPreviewListDTO(result);
        }
    }



