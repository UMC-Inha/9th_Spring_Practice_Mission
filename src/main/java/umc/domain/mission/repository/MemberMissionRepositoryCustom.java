package umc.domain.mission.repository;

import org.springframework.data.domain.Pageable;
import umc.domain.mission.dto.res.MissionResDTO;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<MissionResDTO.OnGoingMissionDto> findOnGoingMissions(Long memberId, Pageable pageable);

    List<MissionResDTO.CompletedMissionDto> findCompletedMissions(Long memberId, Pageable pageable);

    void completeMission(Long memberMissionId);
}
