package umc.domain.mission.converter;

import umc.domain.mission.dto.MissionDto;
import umc.domain.mission.entity.Mission;

public class MissionConverter {

    public static MissionDto toMissionDto(
            Mission mission
    ){
        return MissionDto.builder()
                .id(mission.getId())
                .price(mission.getPrice())
                .point(mission.getPoint())
                .date(mission.getDate())
                .name(mission.getName())
                .status(mission.getStatus())
                .discription(mission.getDiscription())
                .build();
    }
}




