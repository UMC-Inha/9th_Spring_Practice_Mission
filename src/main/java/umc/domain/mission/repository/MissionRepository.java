package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.domain.mission.entity.Mission;


public interface MissionRepository extends JpaRepository<Mission, Long>,MissionRepositoryCustom {
    Page<Mission> findByStoreId(Long storeId, Pageable pageable);
}
