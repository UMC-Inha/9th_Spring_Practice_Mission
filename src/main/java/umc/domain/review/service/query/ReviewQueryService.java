package umc.domain.review.service.query;

import umc.domain.review.dto.res.ReviewResDTO;

import java.util.List;

public interface ReviewQueryService {
    List<ReviewResDTO.ReviewPreviewWorkbookDTO> searchReview(String query, String type);
    List<ReviewResDTO.ReviewPreviewWorkbookDTO> getMyReviews(Long userId, Long storeId, Float starFloor);


    ReviewResDTO.ReviewPreViewListDTO findReview(String storeName, Integer page
    );

    ReviewResDTO.MyReviewPreviewListDTO getMyReviewList(Integer page);

}
