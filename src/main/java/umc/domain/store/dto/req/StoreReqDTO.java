package umc.domain.store.dto.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.domain.store.enums.Day;

import java.time.LocalTime;
import java.util.List;

public class StoreReqDTO {

    //가게 추가 Req DTO
    public record AddStoreDTO(
            @NotBlank
            String name,
            String ownerCode,
            @NotNull
            Long foodId,
            @Valid
            List<BusinessTimeDTO> businessTimes

    ) { }

    public record BusinessTimeDTO(
       @NotNull
       Day day,
       @NotNull
       LocalTime startTime,
       @NotNull
       LocalTime endTime,
       @NotNull
       boolean isClosed,

       LocalTime breakStartTime,
       LocalTime breakEndTime,
       LocalTime lastOrderTime
    ){}
}
