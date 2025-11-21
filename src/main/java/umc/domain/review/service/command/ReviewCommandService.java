package umc.domain.review.service.command;

import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.AddReviewResDTO addReview(ReviewReqDTO.AddReviewDTO req);
}
