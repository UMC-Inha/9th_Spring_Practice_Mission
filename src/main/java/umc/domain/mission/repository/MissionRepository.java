package umc.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.domain.mission.entity.Mission;

import java.awt.print.Pageable;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = """
            
            SELECT mission
                        FROM MemberMission mm
                 JOIN mm.mission mission
                LEFT JOIN  mission.store store
                 where mm.member.id = :memberId
                     and mission.status = 'DONE'
            
            
            """
    )
    List<Object[]> findMyMissions(@Param("memberId") Long memberId, Pageable pageable);




    @Query("""
            SELECT m
             from Mission m
             JOIN m.store s 
              where s.address LIKE %:address%
             and m.status = 'IN_PROGRESS'
            and m.date >= CURRENT_DATE 
            """)
    List<Mission> findAvailableMissions(@Param("address") String address, Pageable pageable);

}
