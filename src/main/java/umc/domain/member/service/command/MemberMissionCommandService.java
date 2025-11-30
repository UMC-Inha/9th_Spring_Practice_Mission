package umc.domain.member.service.command;

import umc.domain.member.dto.req.MemberMissionReqDTO;
import umc.domain.member.dto.res.MemberMissionResDTO;
import umc.domain.member.entity.mapping.MemberMission;

public interface MemberMissionCommandService {
    MemberMissionResDTO.createResDTO addMission(MemberMissionReqDTO.createReqDTO dto);
    MemberMissionResDTO.completeResDTO completeMission(Long memberMissionId);
}
