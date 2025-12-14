package umc.domain.member.converter;

import umc.domain.member.dto.MemberMissionResDTO;
import umc.domain.member.entity.mapping.MemberMission;

public class MemberMissionConverter {

    public static MemberMissionResDTO.complete toCompleteDTO(MemberMission mm) {
        return MemberMissionResDTO.complete.builder()
                .memberMissionId(mm.getId())
                .missionId(mm.getMission().getId())
                .status(mm.getStatus())
                .addedPoint(mm.getMission().getReward())
                .build();
    }
}
