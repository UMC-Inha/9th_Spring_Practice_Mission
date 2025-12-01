package umc.domain.mission.converter;

import umc.domain.mission.dto.res.MissionResDTO;

import java.util.List;

public class MemberMissionConverter {
    public static MissionResDTO.OnGoingMissionListResult toOnGoingMissionListResult(
            Long memberId,
            List<MissionResDTO.OnGoingMissionDto> missions
    ) {
        return MissionResDTO.OnGoingMissionListResult.builder()
                .memberId(memberId)
                .missions(missions)
                .totalCount(missions.size())
                .build();
    }

    public static MissionResDTO.CompletedMissionListResult toCompletedMissionListResult(
            Long memberId,
            List<MissionResDTO.CompletedMissionDto> missions
    ) {
        return MissionResDTO.CompletedMissionListResult.builder()
                .memberId(memberId)
                .missions(missions)
                .totalCount(missions.size())
                .build();
    }
}
