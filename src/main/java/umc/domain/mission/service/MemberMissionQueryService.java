package umc.domain.mission.service;

import org.springframework.data.domain.Pageable;

import static umc.domain.mission.dto.res.MemberMissionResDTO.*;


public interface MemberMissionQueryService {
    OnGoingMissionListResult getOnGoingMissions(Long memberId, Pageable pageable);

    CompletedMissionListResult getCompletedMissions(Long memberId, Pageable pageable);
}
