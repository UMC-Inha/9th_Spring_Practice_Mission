package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;

public interface MemberMissionQueryService {
    MemberMissionResDTO.MissionByStatusListDTO getMissionByStatus(Integer page);
}
