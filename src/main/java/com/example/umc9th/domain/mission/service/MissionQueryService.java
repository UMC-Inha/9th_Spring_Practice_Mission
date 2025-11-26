package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.dto.Res.MissionResDTO;

public interface MissionQueryService {
    MissionResDTO.MissionByStoreListDTO getMissionByStoreListDTO(Long storeId, Integer page);
}
