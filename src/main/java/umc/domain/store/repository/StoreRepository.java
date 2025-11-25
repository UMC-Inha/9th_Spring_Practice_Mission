package umc.domain.store.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.store.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {

    boolean existsByOwnerId(Long ownerId);
    Optional<Store> findByName(String name);
}
