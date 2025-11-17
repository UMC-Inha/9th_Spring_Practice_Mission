package umc.domain.member.service;

import umc.domain.member.dto.res.MemberResDTO;

public interface MemberQueryService {
    MemberResDTO.Mypage getById(Long id);
}
