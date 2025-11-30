package umc.domain.review.service.query;

import org.springframework.data.domain.Pageable;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.dto.res.ReviewResponseDTO;
import umc.global.apiPayload.dto.PageResponseDTO;

import java.util.List;

public interface ReviewQueryService {
    PageResponseDTO<ReviewResponseDTO> searchReview(Long memberId, String type, String query, Pageable pageable);
    PageResponseDTO<ReviewResDTO.ReviewPreViewDTO> findReview(String storeName, Integer page);
    PageResponseDTO<ReviewResDTO.MemberReviewDTO> findMemberReview(Long memberId, Integer page);
}
