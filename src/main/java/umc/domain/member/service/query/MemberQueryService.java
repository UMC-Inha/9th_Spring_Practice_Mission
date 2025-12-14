package umc.domain.member.service.query;

import umc.domain.member.dto.MemberResDTO;

public interface MemberQueryService {

    MemberResDTO.MyPageMemberDTO getMyPageInfo(Long memberId);
}
