package umc.domain.review.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ReviewPhotoDto {

    private String photoUrl;

    @QueryProjection
    public ReviewPhotoDto(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
