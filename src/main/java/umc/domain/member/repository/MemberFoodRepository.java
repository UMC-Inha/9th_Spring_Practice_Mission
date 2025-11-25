package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.domain.member.entity.mapping.MemberFood;

@Repository
public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
