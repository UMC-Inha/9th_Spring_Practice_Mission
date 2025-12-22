package umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Store;

public class MissionConverter {

    //ResSimple -> mission
    public static MissionResDTO.SimpleMissionDTO toSimpleMissionDTO(Mission mission){
        return MissionResDTO.SimpleMissionDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .reward(mission.getReward())
                .build();
    }

    //MissionReqDTO.create -> mission
    public static Mission toMission(MissionReqDTO.CreateMission dto, Store store){
        return Mission.builder()
                .deadline(dto.deadline())
                .content(dto.content())
                .reward(dto.reward())
                .store(store)
                .build();
    }

    // Mission -> Res.MissionPreviewDTO
    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission m) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .missionId(m.getId())
                .storeId(m.getStore().getId())
                .storeName(m.getStore().getName())
                .content(m.getContent())
                .reward(m.getReward())
                .deadline(m.getDeadline())
                .build();
    }
    // Page<Mission> -> Res.MissionPreviewListDTO
    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> page) {
        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionList(page.getContent().stream()
                        .map(MissionConverter::toMissionPreviewDTO)
                        .toList())
                .listSize(page.getNumberOfElements())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

}
