package umc.domain.review.converter;

import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;
import umc.domain.test.dto.res.TestResDTO;
import umc.domain.user.entity.User;

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

    //DTO -> Entity (리뷰 생성용)
    public static Review toReview(ReviewReqDTO.AddReviewDTO req, User user, Store store){
        return Review.builder()
                .user(user)
                .store(store)
                .rating(req.rating())
                .content(req.content())
                .build();
    }

    //Entity -> DTO
    public static ReviewResDTO.AddReviewResDTO toAddReviewResDTO(Review review){
        return ReviewResDTO.AddReviewResDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

}
