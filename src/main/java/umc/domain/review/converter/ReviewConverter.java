package umc.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewReplyDTO;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

import java.time.LocalDate;
import java.util.List;

public class ReviewConverter {

    // Review → ReviewResponseDTO.Detail
    public static ReviewResDTO.Detail toReviewResponseDTO(Review review) {
        return ReviewResDTO.Detail.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .content(review.getContent())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .imageUrls(review.getImages().stream()
                        .map(img -> img.getImageUrl())
                        .toList())
                .replies(review.getReplies().stream()
                        .map(reply -> ReviewReplyDTO.builder()
                                .replyId(reply.getId())
                                .content(reply.getContent())
                                .createdAt(reply.getCreatedAt())
                                .build())
                        .toList())
                .build();
    }

    // List<Review> → List<ReviewResponseDTO.Detail>
    public static List<ReviewResDTO.Detail> toReviewResponseDTOListDetail(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toReviewResponseDTO)
                .toList();
    }

    //ReviewReqDTO.Create -> Review
    public static Review toReview(ReviewReqDTO.ReviewCreate dto, Store store, Member member) {
        return Review.builder()
                .content(dto.content())
                .rating(dto.rating())

                .store(store)
                .member(member)
                .build();
    }

    // Page<Review> -> ReviewPreViewListDTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewDTO)
                        .toList()
                )
                .listSize(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ){
        return ReviewResDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getRating())
                .body(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }


}
