package com.example.umc9th.domain.mission.dto.Res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionByStoreListDTO(
              List<MissionByStoreDTO> missionList,
              Integer listSize,
              Integer totalPage,
              Long totalElements,
              Boolean isFirst,
              Boolean isLast

    ){}
    @Builder
    public record MissionByStoreDTO(
            Long missionId,
            Integer point,
            LocalDateTime deadline
    ) {}

}


