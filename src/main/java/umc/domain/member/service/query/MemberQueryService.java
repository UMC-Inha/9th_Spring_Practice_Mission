package umc.domain.member.service.query;

import jakarta.validation.Valid;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;

public interface MemberQueryService {

    MemberResDTO.MyPageMemberDTO getMyPageInfo(Long memberId);

    MemberResDTO.LoginDTO login(MemberReqDTO.@Valid LoginDTO dto);
}
