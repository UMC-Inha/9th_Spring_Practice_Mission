package umc.domain.member.converter;

import umc.domain.member.dto.membermission.MemberMissionResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.enums.Status;
import umc.domain.misson.entity.Mission;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Mission mission, Member member) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(Status.PENDING)
                .endDate(mission.getEndDate())
                .build();
    }

    public static MemberMissionResDTO.challengeMissionResDTO toChallengeMissionResDTO(
            MemberMission mission
    ) {
        return MemberMissionResDTO.challengeMissionResDTO.builder()
                .missionId(mission.getMission().getId())
                .missionDescription(mission.getMission().getDescription())
                .memberId(mission.getMember().getId())
                .status(mission.getStatus())
                .endDate(mission.getEndDate())
                .build();
    }
}
