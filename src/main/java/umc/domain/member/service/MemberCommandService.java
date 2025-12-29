package umc.domain.member.service;

import umc.domain.member.dtos.MemberReqDTO;
import umc.domain.member.dtos.MemberResDTO;

public interface MemberCommandService {

    MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto);
}
