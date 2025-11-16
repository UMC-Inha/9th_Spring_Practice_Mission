package umc.domain.member.service.common;

import org.springframework.transaction.annotation.Transactional;

public interface MemberMissionCommandService {
    @Transactional
    void acceptMission(Long memberId, Long missionId);
}
