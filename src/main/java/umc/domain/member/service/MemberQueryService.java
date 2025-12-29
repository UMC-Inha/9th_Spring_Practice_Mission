package umc.domain.member.service;

import umc.domain.member.dtos.MemberReqDTO;
import umc.domain.member.dtos.MemberResDTO;

public interface MemberQueryService {

    MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto);
}


