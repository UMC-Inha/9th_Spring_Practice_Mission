package umc.domain.review.service.query;

import umc.domain.review.dto.MemberReviewResDTO;

public interface MemberReviewQueryService {
    MemberReviewResDTO.ReviewPreViewListDTO findReview(Long memberId, Integer page);
}
