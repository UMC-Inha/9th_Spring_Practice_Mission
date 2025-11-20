package umc.domain.mission.converter;

import umc.domain.mission.dto.res.MissionDetailDTO;
import umc.domain.mission.entity.Mission;

public class MissionCreateConverter {

    // Mission 엔티티 -> MissionDetail 응답 DTO
    public static MissionDetailDTO toMissionDetailDTO(Mission mission) {
        return MissionDetailDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .deadline(mission.getDeadline())
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .leastAmount(mission.getLeastAmount())
                .build();
    }

}
