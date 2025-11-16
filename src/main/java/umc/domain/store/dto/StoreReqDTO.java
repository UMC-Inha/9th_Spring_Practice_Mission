package umc.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import umc.domain.store.entity.StoreType;

public class StoreReqDTO {

    public record CreateDTO(
            @NotNull(message = "가게 타입은 필수입니다.")
            StoreType type,

            @NotBlank(message = "가게 이름은 필수입니다.")
            String name,

            @NotBlank(message = "주소는 필수입니다.")
            String address,

            @NotNull(message = "행정구역 ID는 필수입니다.")
            Long districtId
    ) {}
}
