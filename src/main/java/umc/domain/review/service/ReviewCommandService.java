package umc.domain.review.service;

import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;

public interface ReviewCommandService {

    ReviewResDTO.ReviewInfo writeReview(
            Long memberId,
            Long StoreId,
            ReviewReqDTO.CreateReview req
    );
}
