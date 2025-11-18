package umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.domain.member.dto.MyPageDto;
import umc.domain.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("""
            select new umc.domain.member.dto.MyPageDto(
                    m.nickname,
                    m.email,
                    m.phoneNumber,
                    m.points
            )
            from Member m
            where m.id = :id
            """)
    MyPageDto findMyPageById(@Param("id") long id);
}
