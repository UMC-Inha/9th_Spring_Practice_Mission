package umc.domain.review.service.command;

import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.ReviewCreateDTO createReview(
            Long storeId,
            Long memberId,
            ReviewReqDTO.ReviewCreateDTO reviewEnrollDTO);
}
