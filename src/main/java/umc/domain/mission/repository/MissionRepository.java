package umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.Mission;
import umc.domain.user.enums.MissionStatus;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    //4.2 홈 화면 - 특정 지역의 도전 가능 미션 목록 조회

    @Query(value = "SELECT m FROM Mission m " +
                   "JOIN FETCH m.store s " + //미션 조회할 때, 미션에 연결된 가게 정보도 어차피 쓸거니까!
                   "WHERE s.region.id = :regionId " +
                   "AND m.isActive = true " +
                    "AND NOT EXISTS (" + //서브쿼리에 존재하지 않는 미션만 조회 (SUCCESS, IN_PROGRESS)
                    "   SELECT um FROM UserMission um " +
                    "   WHERE um.user.id = :userId " +
                    "   AND um.mission = m " +
                    "   AND um.status IN :statuses" +
                    ")",
            countQuery = "SELECT COUNT(m) FROM Mission m " + //전체 쿼리 개수 조회(페이징을 위해!)
                    "JOIN m.store s " +
                    "WHERE s.region.id = :regionId " +
                    "AND m.isActive = true " +
                    "AND NOT EXISTS (" +
                    "    SELECT um FROM UserMission um " +
                    "    WHERE um.user.id = :userId " +
                    "    AND um.mission = m " +
                    "    AND um.status IN :statuses" +
                    ")")
    Page<Mission> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("userId") Long userId,
            @Param("statuses")List<MissionStatus> statuses,
            Pageable pageable
            );
}
