package umc.domain.store.dto.res;

import lombok.Builder;
import umc.domain.store.enums.Day;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class StoreResDTO {

    @Builder
    public record  AddStoreResDTO(
       Long storeId,
       String name,
       Long foodId,
       Long regionId,
       List<BusinessTimeResDTO> businessTimes,
       LocalDateTime createdAt
    ){}

    @Builder
    public record BusinessTimeResDTO(
       Day day,
       LocalTime startTime,
       LocalTime endTime,
       boolean isClosed
    ){}
}
