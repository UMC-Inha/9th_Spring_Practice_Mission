package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    /* UI 아래쪽 지역에 따른 MY MISSION, 아직 성공하지 않았거나 도전하지 않은 미션
    SELECT
        ms.mission_id,
        ms.text	AS mission_content,
        st.store_id, st.store_name, st.store_type,
        ms.deadline, ms.point, ms.success, rg.region_id
    FROM mission AS ms
    JOIN store AS st ON st.store_id = ms.store_id
    JOIN region AS rg ON rg.store_id = st.store_id
    WHERE rg.region_id = ?
	AND NOT EXISTS (
    SELECT mm FROM member_mission AS mm
	WHERE mm.mission_id = ms.mission_id
	AND mm.member_id = ?
	AND mm.status IN ('IN_PROGRESS', 'SUCCESS')
     )
    ORDER BY ms.deadline ASC
    LIMIT 5;
    */

    @Query(value = "SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.region r " +
            "WHERE r.id = :regionID " +
            "AND NOT EXISTS (" +
            "SELECT mm FROM MemberMission mm " +
            "WHERE mm.mission = m " +
            "AND mm.member.id = :memberID " +
            "AND mm.status IN :statuses) " +
            "ORDER BY m.deadline ASC ",

            countQuery = "SELECT COUNT(m.id) FROM Mission m " +
                    "WHERE m.store.region.id = :regionID " +
                    "AND NOT EXISTS (" +
                    "SELECT mm FROM MemberMission mm " +
                    "WHERE mm.mission = m " +
                    "AND mm.member.id = :memberID " +
                    "AND mm.status IN :statuses) "
            )
    Page<Mission> findAvailableMissionInRegion(
            @Param("memberID") Long memberID,
            @Param("regionID") Long regionID,
            @Param("statuses") List<Boolean> statuses,
            Pageable pageable
            );
}
