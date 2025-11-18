package umc.domain.review.converter;

import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.test.dto.res.TestResDTO;

import java.time.LocalDate;

public class ReviewConverter {

    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review){
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }


}
