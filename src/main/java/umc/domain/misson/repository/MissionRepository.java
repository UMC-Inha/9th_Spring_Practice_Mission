package umc.domain.misson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.misson.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
