package umc.domain.review.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class ReviewReqDTO {

    public record SearchDTO(

            @NotBlank(message = "가게 이름은 필수 입력값입니다.")
            String storeName,

            @Min(value = 1, message = "별점 범위는 최소 1 이상이어야 합니다.")
            @Max(value = 5, message = "별점 범위는 최대 5 이하이어야 합니다.")
            Integer starRange

    ) {
    }

    public record CreateDTO(

            @NotNull(message = "가게 ID는 필수 입력값입니다.")
            Long storeId,

            @NotBlank(message = "리뷰 내용은 필수 입력값입니다.")
            @Size(max = 300, message = "리뷰 내용은 최대 300자까지 입력할 수 있습니다.")
            String content,

            @NotNull(message = "별점은 필수 입력값입니다.")
            @DecimalMin(value = "0.0", inclusive = false, message = "별점은 0보다 커야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0 이하여야 합니다.")
            BigDecimal star,

            @Size(max = 10, message = "리뷰 사진은 최대 10개까지 업로드할 수 있습니다.")
            List<@NotBlank(message = "사진 URL은 빈 문자열이 될 수 없습니다.")
            @Pattern(regexp = "^(http|https)://.*$", message = "사진 URL은 http 또는 https로 시작해야 합니다.")
                    String> photoUrls

    ) {
    }
}
