package umc.domain.review.converter;

import umc.domain.review.dto.FindMyReviewDto;
import umc.domain.review.entity.Review;

public class ReviewConverter {



    public static FindMyReviewDto toReviewDTO(
            Review review
    ){
        return FindMyReviewDto.builder()
                .store(review.getStore().getName())
                .rate(review.getRate())
                .discription(review.getDiscription())
                .build();
    }
}
