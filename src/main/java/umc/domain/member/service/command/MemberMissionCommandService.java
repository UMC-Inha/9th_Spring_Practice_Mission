package umc.domain.member.service.command;

import umc.domain.member.dto.req.MemberMissionReqDTO;
import umc.domain.member.dto.res.MemberMissionResDTO;

public interface MemberMissionCommandService {
    MemberMissionResDTO.createResDTO addMission(MemberMissionReqDTO.createReqDTO dto);
}
