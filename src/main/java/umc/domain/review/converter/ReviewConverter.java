package umc.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;
import umc.domain.user.entity.User;

import java.time.LocalDate;

public class ReviewConverter {

    public static ReviewResDTO.ReviewPreviewWorkbookDTO toReviewPreviewBookDTO(Review review){
        return ReviewResDTO.ReviewPreviewWorkbookDTO.builder()
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

    //result -> DTO
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreviewListDTO(
            Page<Review> result
    ){
        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviewList(result.getContent().stream().map(ReviewConverter::toReviewPreviewDTO).toList()) //실제 리뷰 내용물
                .listSize(result.getSize()) //한 페이지에 몇 개씩 보여주는지
                .totalPage(result.getTotalPages()) // 전체 페이지가 몇 쪽인지
                .totalElements(result.getTotalElements()) //전체 리뷰 개수가 몇 개인지
                .isFirst(result.isFirst()) //지금 보고 있는 게 첫 페이지인지
                .isLast(result.isLast())
                .build();
    }

    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review){
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

}
