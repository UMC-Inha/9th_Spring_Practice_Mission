package umc.domain.review.converter;

import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.domain.review.dto.MemberReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.entity.ReviewPhoto;

public class MemberReviewConverter {

    public static MemberReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ) {
        return MemberReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(
                        result.getContent().stream()
                                .map(MemberReviewConverter::toReviewPreviewDTO)
                                .collect(Collectors.toList())
                )
                .listSize(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }


    public static MemberReviewResDTO.ReviewPreViewDTO toReviewPreviewDTO(
            Review review
    ) {
        return MemberReviewResDTO.ReviewPreViewDTO.builder()
                .score(review.getStar())
                .body(review.getContent())
                .photoUrls(
                        review.getPhotos().stream()
                                .map(ReviewPhoto::getPhotoUrl)
                                .collect(Collectors.toList())
                )
                .createdAt(review.getCreatedAt().toLocalDate())
                .reviewReply(toReviewReplyDTO(review))
                .build();
    }

    public static MemberReviewResDTO.ReviewReplyDTO toReviewReplyDTO(Review review) {

        if (review.getReviewReply() == null) {
            return null;
        }

        return MemberReviewResDTO.ReviewReplyDTO.builder()
                .body(review.getReviewReply().getContent())
                .createdAt(review.getReviewReply().getCreatedAt().toLocalDate())
                .build();
    }

}
