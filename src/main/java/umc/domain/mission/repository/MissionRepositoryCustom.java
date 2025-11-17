package umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.domain.mission.entity.Mission;

public interface MissionRepositoryCustom {
    Page<Mission> findByName(String name, Pageable pageable);
}
