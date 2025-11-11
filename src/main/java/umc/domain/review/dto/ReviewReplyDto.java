package umc.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ReviewReplyDto {
    private String content;

    @QueryProjection
    public ReviewReplyDto(String content) {
        this.content = content;
    }
}
