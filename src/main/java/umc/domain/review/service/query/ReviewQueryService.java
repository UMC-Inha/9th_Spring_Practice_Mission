package umc.domain.review.service.query;

import umc.domain.review.dto.res.ReviewResDTO;

import java.util.List;

public interface ReviewQueryService {
    List<ReviewResDTO.ReviewPreviewDTO> searchReview(String query, String type);
    List<ReviewResDTO.ReviewPreviewDTO> getMyReviews(Long userId, Long storeId, Integer starFloor);
}
