package umc.domain.review.service;

import org.springframework.data.domain.Pageable;

import umc.domain.review.dto.res.ReviewResDTO;
import static umc.domain.review.dto.req.ReviewReqDTO.*;

public interface ReviewService  {
    ReviewResDTO.MyReviewListResponse getMyReviews(Long memberId, MyReviewReqDTO reqDTO, Pageable pageable);

}
