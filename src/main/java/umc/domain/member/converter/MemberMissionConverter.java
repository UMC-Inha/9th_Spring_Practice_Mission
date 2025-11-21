package umc.domain.member.converter;

import umc.domain.member.dto.req.MemberMissionReqDTO;
import umc.domain.member.dto.res.MemberMissionResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.mission.entity.Mission;

public class MemberMissionConverter {

    // Entity -> DTO
    public static MemberMissionResDTO.createResDTO toResDTO(
        MemberMission memberMission
    ){
        return MemberMissionResDTO.createResDTO.builder()
                .memberMissionId(memberMission.getId())
                .deadline(memberMission.getDeadline())
                .state(memberMission.getState())
                .build();
    }

    // DTO -> Entity
    public static MemberMission toMemberMission(
            MemberMissionReqDTO.createReqDTO dto,
            Member member,
            Mission mission
    ){
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }
}
