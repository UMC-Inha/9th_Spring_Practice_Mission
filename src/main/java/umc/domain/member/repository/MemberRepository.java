package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m.name, m.email, m.phone_num FROM Member m WHERE m.id = :id")
    List<Object[]> findMemberInfo(@Param("id") Long id);


    Optional<Member> findByEmail(String email);
}
