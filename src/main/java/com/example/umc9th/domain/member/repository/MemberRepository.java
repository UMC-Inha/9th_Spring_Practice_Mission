package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    /* 마이페이지 화면 쿼리
    SELECT name, email, phone_number, phone_certification, point
    FROM member
    WHERE member_id = 1
     */

    // 특정 회원 ID 통해 회원의 모든 정보 조회
    // JpaRepository의 findById(Long id) 사용가능하지만
    // 명시적으로 메서드 작성 가능

    Optional<Member> findById(Long id);
}
