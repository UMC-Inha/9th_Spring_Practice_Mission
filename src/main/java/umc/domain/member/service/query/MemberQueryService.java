package umc.domain.member.service.query;

import jakarta.validation.Valid;
import umc.domain.member.dto.GetMemberResponse;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO.LoginDTO;

public interface MemberQueryService {
    GetMemberResponse getMember(Long memberId);

    LoginDTO login(MemberReqDTO.@Valid LoginDTO dto);
}
