package umc.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.user.entity.Member;

import java.util.List;

public interface UserRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m.name, m.email, m.phone_num FROM Member m WHERE m.id = :id")
    List<Object[]> findMemberInfo(@Param("id") Long id);
}
