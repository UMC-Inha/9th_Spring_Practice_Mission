package umc.domain.mission.converter;

import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;

public class MissionConverter {
    public static MissionResDTO.MissionResponseDTO toMissionResponseDTO(Mission mission) {
        return MissionResDTO.MissionResponseDTO.builder()
                .id(mission.getId())
                .content(mission.getContent())
                .point(mission.getPoint())
                .period(mission.getPeriod())
                .storeName(mission.getStore().getName())
                .build();
    }

    // Entity -> DTO
    public static MissionResDTO.CreateResDTO toCreateResDTO(Mission mission) {
        return MissionResDTO.CreateResDTO.builder()
                .missionId(mission.getId())
                .build();
    }

    // DTO -> Entity
    public static Mission toMission(
            MissionReqDTO.CreateReqDTO dto
    ){
        return Mission.builder()
                .content(dto.content())
                .authCode(dto.authCode())
                .period(dto.period())
                .point(dto.point())
                .build();

    }
}
