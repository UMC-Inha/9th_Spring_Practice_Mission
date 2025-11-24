package com.example.umc9th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MissionChallengeDTO {
    Long memberId;
    Long missionId;
    Boolean status;
}
