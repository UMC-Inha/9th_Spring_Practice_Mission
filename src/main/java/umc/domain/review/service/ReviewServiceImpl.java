package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.review.converter.MyReviewConverter;
import umc.domain.review.dto.req.ReviewReqDTO.MyReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.review.repository.query.MyReviewQuery;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl  implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.MyReviewListResponse getMyReviews(Long memberId, MyReviewReqDTO reqDTO, Pageable pageable) {

        MyReviewQuery query = MyReviewConverter.toMyReviewQuery(reqDTO);

        Page<ReviewResDTO.MyReviewResponse> page =
                reviewRepository.findMyReviews(memberId, query, pageable);

        // 🔹 여기서 Page -> MyReviewListResponse로 변환
        return MyReviewConverter.toMyReviewListResponse(page);
    }
}
