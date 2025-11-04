package umc.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.math.BigDecimal;
import java.util.List;

public class ReviewDto {
    private Long review_id;
    private String memberName;
    private BigDecimal review_rating;
    private List<ReviewPhotoDto> reviewPhotoDtos;
    private List<ReviewReplyDto> reviewReplyDtos;

    @QueryProjection
    public ReviewDto(Long review_id, String memberName, BigDecimal review_rating,
                     List<ReviewPhotoDto> reviewPhotoDtos, List<ReviewReplyDto> reviewReplyDtos) {
        this.review_id = review_id;
        this.memberName = memberName;
        this.review_rating = review_rating;
        this.reviewPhotoDtos = reviewPhotoDtos;
        this.reviewReplyDtos = reviewReplyDtos;
    }
}
