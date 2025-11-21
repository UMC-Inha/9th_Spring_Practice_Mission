package umc.domain.review.converter;

import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.dto.res.ReviewResponseDTO;
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

    // Entity -> DTO
    public static ReviewResDTO.CreateResDTO toResDTO(Review review){
        return ReviewResDTO.CreateResDTO.builder()
                .reviewId(review.getId())
                .createAt(review.getCreatedAt())
                .build();
    }

    //DTO -> Entity
    public static Review toReview(
            ReviewReqDTO.CreateReviewDTO dto
    ){
        return Review.builder()
                .grade(dto.grade())
                .content(dto.content())
                .build();
    }
}
