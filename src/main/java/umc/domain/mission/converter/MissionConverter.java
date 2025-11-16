package umc.domain.mission.converter;

import umc.domain.mission.dto.MissionResponseDTO;
import umc.domain.mission.entity.Mission;

public class MissionConverter {
    public static MissionResponseDTO toMissionResponseDTO(Mission mission) {
        return MissionResponseDTO.builder()
                .id(mission.getId())
                .content(mission.getContent())
                .point(mission.getPoint())
                .period(mission.getPeriod())
                .storeName(mission.getStore().getName())
                .build();
    }
}
