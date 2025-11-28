package umc.domain.misson.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.misson.entity.Mission;
import umc.domain.store.entity.Store;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionQueryDsl {
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}
