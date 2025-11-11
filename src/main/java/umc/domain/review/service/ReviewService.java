package umc.domain.review.service;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.region.entity.QRegion;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.entity.QReview;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;



    //워크북 실습 코드
    public List<Review> searchReview(String query, String type) {

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

        //Repositorry 사용 & 결과 매핑
        List<Review> reviewList = reviewRepository.searchReview(builder);

        //리턴
        return reviewList;
    }

    //과제(미션) 코드
    public List<ReviewResponseDTO.ReviewPreviewDTO> getMyReviews(Long userId, Long storeId, Integer starFloor) {

        //리포지토리에 파라미터 그대루 넘기기
        List<Review>reviews = reviewRepository.findMyReviewsFiltered(userId, storeId, starFloor);

        //Entity List를 Dto List로 변환
        return reviews.stream()
                .map(this::convertToPreviewDTO)
                .collect(Collectors.toList());
    }

    //엔티티를 DTO로 변환하는 메서드
    private ReviewResponseDTO.ReviewPreviewDTO convertToPreviewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }
    }

