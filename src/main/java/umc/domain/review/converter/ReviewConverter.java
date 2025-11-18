package umc.domain.review.converter;

import java.util.List;
import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

public class ReviewConverter {

    public static ReviewResDTO.ReviewCreateDTO toReviewEnrollDTO(Review review, List<String> photoUrls) {
        return ReviewResDTO.ReviewCreateDTO.builder()
                .reviewId(review.getId())
                .starRating(review.getStarRating())
                .photoUrls(photoUrls)
                .build();
    }

    public static Review toReview(Store store, Member member, ReviewReqDTO.ReviewCreateDTO dto) {
        return Review.builder()
                .store(store)
                .member(member)
                .content(dto.content())
                .starRating(dto.starRating())
                .build();
    }
}
