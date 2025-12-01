package umc.domain.mission.converter;

import umc.domain.member.entity.Member;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.mapping.MemberMission;

public class GetChallengeMissionConverter {

    //DTO -> Entity
    public static MemberMission toMemberMission(Member member, Mission mission){
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .isCompleted(false)
                .build();
    }

    public static MissionResDTO.GetChallengeMissionResDTO toGetChallengeMissionResDTO(MemberMission memberMission){
        return MissionResDTO.GetChallengeMissionResDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .isCompleted(memberMission.isCompleted())
                .build();
    }

}

