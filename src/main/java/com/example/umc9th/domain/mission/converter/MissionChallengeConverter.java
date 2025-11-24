package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.MissionChallengeDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

public class MissionChallengeConverter {
    public static MemberMission toMemberMission(
            Mission mission,
            Member member
    ) {
        return MemberMission.builder()
                .mission(mission)
                .member(member)
                .status(false) // true = success, false = in_progress
                .build();
    }

    public static MissionChallengeDTO toMissionChallengeDTO(MemberMission memberMission) {
        return MissionChallengeDTO.builder()
                .missionId(memberMission.getMission().getId())
                .memberId(memberMission.getMember().getId())
                .status(memberMission.isStatus())
                .build();
    }
}
