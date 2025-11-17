package umc.domain.mission.converter;

import umc.domain.mission.dto.res.CompletedMissionDto;
import umc.domain.mission.dto.res.MemberMissionResDTO;
import umc.domain.mission.dto.res.OnGoingMissionDto;

import java.util.List;

public class MemberMissionConverter {
    public static MemberMissionResDTO.OnGoingMissionListResult toOnGoingMissionListResult(
            Long memberId,
            List<OnGoingMissionDto> missions
    ) {
        return MemberMissionResDTO.OnGoingMissionListResult.builder()
                .memberId(memberId)
                .missions(missions)
                .totalCount(missions.size())
                .build();
    }

    public static MemberMissionResDTO.CompletedMissionListResult toCompletedMissionListResult(
            Long memberId,
            List<CompletedMissionDto> missions
    ) {
        return MemberMissionResDTO.CompletedMissionListResult.builder()
                .memberId(memberId)
                .missions(missions)
                .totalCount(missions.size())
                .build();
    }
}
