package umc.domain.member.repository.membermission;

import java.util.Optional;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.misson.entity.Mission;

public interface MemberMissionQueryDsl {
    Optional<MemberMission> findMemberMissionByMemberAndMission(Member member, Mission mission);
}
