package umc.domain.member.service.common;

import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signUp(MemberReqDTO.JoinDTO dto);

    void updateMemberName(Long memberId, String name);

    void deleteMember(Long memberId, boolean hard);
}
