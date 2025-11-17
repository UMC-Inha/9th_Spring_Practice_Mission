package umc.domain.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import umc.domain.review.dto.req.MyReviewReqDTO;
import umc.domain.review.dto.res.MyReviewResDTO;

public interface ReviewService  {
    Page<MyReviewResDTO> getMyReviews(Long memberId, MyReviewReqDTO reqDTO, Pageable pageable);

}