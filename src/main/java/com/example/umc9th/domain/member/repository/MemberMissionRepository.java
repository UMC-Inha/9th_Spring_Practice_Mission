package com.example.umc9th.domain.member.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    /* 내가 진행중, 진행완료한 미션 모아서보는 쿼리
    SELECT
    ms.mission_id, ms.text AS mission_content, ms.point,
    ms.price, st.store_id, st.store_name
    FROM mission AS ms
    JOIN store AS st ON st.store_id = ms.store_id
    WHERE ms.member_id = ?
    AND ms.status = 'IN_PROGRESS'
    ORDER BY ms.updated_at ASC
    LIMIT 5;
     */

    @Query(value = "SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberID AND mm.status = :statuses ORDER BY mm.updatedAt ASC" ,
    countQuery = "SELECT COUNT(mm.id) FROM MemberMission mm " +
            "WHERE mm.member.id = :memberID AND mm.status = :statuses ")
    // 미션의 전체 개수를 세기 위해 countQuery 사용, 페이징할 때 필요하기 때문에

    Page<MemberMission>
    findMissionPageByStatus(@Param("memberID") Long memberId,
                            @Param("statuses")Boolean status,
                            Pageable pageable
                            );

    /*  홈화면에서 7/10 나타내는 쿼리 작성
    SELECT COUNT(mm.id)
    FROM member_mission AS mm
    JOIN mission AS m ON mm.mission_id = m.id
    JOIN store AS s ON m.store_id = s.id
    JOIN region AS r ON s.region_id = r.id
    WHERE mm.member_id = ?
    AND mm.status = 'SUCCESS'
    AND r.id = ?;
    */

    @Query("SELECT COUNT(mm.id)" +
            "FROM MemberMission mm " +
            "JOIN mm.mission m " +
            "JOIN m.store s " +
            "JOIN s.region r " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.status = :success " +
            "AND r.id = :regionId")

    Long countSucessfulMissionByMemberAndRegion(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId,
            @Param("success") Boolean success
    );
}
