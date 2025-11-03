package umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.mission.entity.Mission;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("""
        select m
        from Mission m
        join m.store s
        where s.region.id = :regionId
          and not exists (
              select mm
              from MemberMission mm
              where mm.mission = m
                and mm.member.id = :memberId
          )
    """)
    List<MemberMission> findActiveOrCompletedMissionsOrderByDeadline(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId
    );
    /*Page<Mission> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId,
            Pageable pageable
    );*/
    //아직 Service를 안만들어서 Pageable를 쓸 수가 없다.
}
