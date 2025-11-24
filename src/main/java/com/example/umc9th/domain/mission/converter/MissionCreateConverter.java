package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MissionCreateDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Store;

public class MissionCreateConverter {

    public static Mission toMission(MissionCreateDTO dto, Store store) {
        return Mission.builder()
                .store(store)
                .conditional(dto.getConditional())
                .point(dto.getPoint())
                .deadline(dto.getDeadline())
                .build();
    }
}
