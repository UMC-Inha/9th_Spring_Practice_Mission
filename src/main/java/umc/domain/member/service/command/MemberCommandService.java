package umc.domain.member.service.command;

import umc.domain.member.dto.req.MemberReqDTO;
import umc.domain.member.dto.res.MemberResponseDTO;

public interface MemberCommandService {

    MemberResponseDTO.SignupResponseDTO signup(MemberReqDTO.SignupRequestDTO dto);


}
