package umc.domain.store.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String name);
}
