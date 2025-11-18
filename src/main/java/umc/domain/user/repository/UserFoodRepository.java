package umc.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.user.mapping.UserFood;

public interface UserFoodRepository extends JpaRepository<UserFood, Long> {
}
