package umc.domain.store.dto.req;


import jakarta.validation.constraints.NotBlank;
import umc.global.annotation.ExistFood;

public class StoreReqDTO {
    public record CreateRequestDTO(
            @NotBlank(message = "가게 이름은 필수 입력 값입니다.")
            String name,
            String info,
            Long regionId,
            @ExistFood
            Long foodId
    ){}
}
