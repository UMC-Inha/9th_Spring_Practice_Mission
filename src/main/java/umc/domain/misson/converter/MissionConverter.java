package umc.domain.misson.converter;

import umc.domain.misson.dto.MissionReqDTO;
import umc.domain.misson.dto.MissionResDTO;
import umc.domain.misson.entity.Mission;
import umc.domain.store.entity.Store;

public class MissionConverter {

    public static Mission toMission(Store store, MissionReqDTO.createMissionReqDTO reqDTO, int uniqueNumber) {
        return Mission.builder()
                .description(reqDTO.description())
                .points(reqDTO.points())
                .uniqueNumber(uniqueNumber)
                .endDate(reqDTO.endDate())
                .store(store)
                .build();
    }

    public static MissionResDTO.createMissionResDTO toCreateMissionResDTO(Mission mission) {
        return MissionResDTO.createMissionResDTO.builder()
                .missionId(mission.getId())
                .description(mission.getDescription())
                .points(mission.getPoints())
                .uniqueNumber(mission.getUniqueNumber())
                .endDate(mission.getEndDate())
                .storeId(mission.getStore().getId())
                .build();
    }
}
