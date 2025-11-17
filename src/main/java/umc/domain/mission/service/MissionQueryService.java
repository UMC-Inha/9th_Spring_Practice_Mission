package umc.domain.mission.service;

import org.springframework.data.domain.Pageable;
import umc.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {
    MissionResDTO.MissionListResult findByLocationName(String locationName, Pageable pageable);
}
