package umc.domain.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.location.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
