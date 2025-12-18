package umc.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;


@Getter
@AllArgsConstructor
public class ReqFindMyReviewDto {

    @NotNull
    Long storeId;


    @Min(value = 1, message = "rate는 1 이상이어야 합니다")
    @Max(value = 5, message = "rate는 5 이하여야 합니다")
    Integer rate;

    @NotEmpty
    Boolean sortByStore;

    @NotEmpty
    Boolean sortByRate;


}
