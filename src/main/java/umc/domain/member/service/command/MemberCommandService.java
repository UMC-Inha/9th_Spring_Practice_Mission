package umc.domain.member.service.command;

import umc.domain.member.dto.member.MemberReqDTO;
import umc.domain.member.dto.member.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signUp(
            MemberReqDTO.JoinDTO dto
    );
}
