package com.example.umc9th.domain.member.dto.res;

import com.example.umc9th.domain.member.enums.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResDTO {
    @Builder
    public record MissionByStatusListDTO(
            List<MissionByStatusDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    // 내가 진행중인 미션목록
    @Builder
    public record MissionByStatusDTO(
            Long memberMissionId,
            LocalDateTime deadline,
            Integer point,
            Boolean isComplete,
            String storeName
    ) {}

    @Builder
    public record SuccessResDTO(
            Long memberMissionId,
            Integer point,
            String storeName,
            Boolean status
    ) {}
}
