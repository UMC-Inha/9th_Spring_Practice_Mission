package umc.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.domain.member.entity.Member;
import umc.domain.mission.entity.MemberMission;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.enums.Status;
import umc.domain.store.entity.Store;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {


    @Query("""
    select mm.mission
    from MemberMission mm
    where mm.member = :member and mm.status = :status
""")
    Page<Mission> findAllByMemberAndStatus(Member member, Status status, Pageable pageable);

}
