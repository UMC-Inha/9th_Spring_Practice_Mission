package umc.domain.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import umc.domain.review.dto.res.ReviewResDTO;
import static umc.domain.review.dto.req.ReviewReqDTO.*;

public interface ReviewService  {
    Page<ReviewResDTO.MyReviewResDTO> getMyReviews(Long memberId, MyReviewReqDTO reqDTO, Pageable pageable);

}
