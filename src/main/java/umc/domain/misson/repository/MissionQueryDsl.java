package umc.domain.misson.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.domain.member.entity.Member;
import umc.domain.misson.entity.Mission;

public interface MissionQueryDsl {
    Page<Mission> findOngoingMissionsByMember(Member member, Pageable pageable);
}
