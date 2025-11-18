package umc.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.store.entity.BusinessTime;

public interface BusinessTimeRepository extends JpaRepository<BusinessTime, Long> {
}
