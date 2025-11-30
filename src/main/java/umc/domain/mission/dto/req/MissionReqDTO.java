package umc.domain.mission.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import umc.global.annotation.ExistStore;
import umc.global.annotation.ValidPage;

public class MissionReqDTO {
    public record CreateReqDTO(
            String content,
            @NotNull(message = "포인트는 필수 입력 값입니다.")
            @Positive(message = "포인트는 0보다 큰 값이어야 합니다.")
            Integer point,
            @NotBlank(message = "인증 코드는 필수 입력 값입니다.")
            String authCode,
            @NotNull(message = "미션 기간은 필수 입력 값입니다.")
            @Positive(message = "미션 기간은 0보다 큰 값이어야 합니다.")
            Integer period,
            @ExistStore
            Long storeId
    ){}

    public record FindStoreMissionDTO(
            @ValidPage
            Integer page
    ){
        public FindStoreMissionDTO {
            if (page == null) page = 1;
        }
    }
}
