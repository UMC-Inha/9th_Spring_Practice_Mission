package umc.domain.misson.converter;

import org.springframework.data.domain.Page;
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

    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .description(mission.getDescription())
                .points(mission.getPoints())
                .endDate(mission.getEndDate())
                .build();
    }

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missions) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionPreviewDTOList(missions.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList())
                .listSize(missions.getSize())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }
}
