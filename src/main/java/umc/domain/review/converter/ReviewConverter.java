package umc.domain.review.converter;

import java.util.List;
import org.springframework.data.domain.Page;
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

    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .starRating(review.getStarRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static ReviewResDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviews) {
        return ReviewResDTO.ReviewPreviewListDTO.builder()
                .reviewList(reviews.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList())
                .listSize(reviews.getSize())
                .totalPage(reviews.getTotalPages())
                .totalElements(reviews.getTotalElements())
                .isFirst(reviews.isFirst())
                .isLast(reviews.isLast())
                .build();
    }
}
