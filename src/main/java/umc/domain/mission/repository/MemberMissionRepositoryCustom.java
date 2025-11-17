package umc.domain.mission.repository;

import org.springframework.data.domain.Pageable;
import umc.domain.mission.dto.res.CompletedMissionDto;
import umc.domain.mission.dto.res.OnGoingMissionDto;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<OnGoingMissionDto> findOnGoingMissions(Long memberId, Pageable pageable);

    List<CompletedMissionDto> findCompletedMissions(Long memberId, Pageable pageable);
}
