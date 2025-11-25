package umc.domain.member.service.command;

import umc.domain.member.dto.membermission.MemberMissionResDTO;

public interface MemberMissionCommandService {

    MemberMissionResDTO.challengeMissionResDTO challengeMission(
            Long missionId,
            Long memberId
    );
}
