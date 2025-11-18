package umc.domain.review.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateReviewDto {


    @NotBlank
    Integer rate;

    @NotBlank
    String description;

    @NotBlank
    String photo;


}
