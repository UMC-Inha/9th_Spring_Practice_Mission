package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.domain.member.entity.Member;

import java.util.Optional;


public interface MemberRepository  extends JpaRepository<Member,Long> {
    Optional<Member> findByIdAndDeletedFalse(Long id);  // 회원이 없을 경우를 대비해서 optional을 사용.

}

