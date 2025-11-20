package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.converter.MissionCreateConverter;
import com.example.umc9th.domain.mission.dto.MissionCreateDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreErrorCode;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCreateService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public Long createMission(MissionCreateDTO missionCreateDTO){
        Store store = storeRepository.findById(missionCreateDTO.getStoreId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionCreateConverter.toMission(missionCreateDTO, store);
        missionRepository.save(mission);

        return mission.getId();
    }
}
