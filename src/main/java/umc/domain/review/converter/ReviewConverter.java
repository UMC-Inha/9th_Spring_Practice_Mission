package umc.domain.review.converter;

import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResponseDTO toResponseDTO(Review review){
        return ReviewResponseDTO.builder()
                .id(review.getId())
                .content(review.getContent())
                .grade(review.getGrade())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
