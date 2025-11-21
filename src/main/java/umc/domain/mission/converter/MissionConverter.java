package umc.domain.mission.converter;

import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Store;

public class MissionConverter {

    //DTO -> Entity
    public static Mission toMission(MissionReqDTO.AddMissionReqDTO req, Store store
                                    ){
        return Mission.builder()
                .store(store)
                .rewardPoint(req.rewardPoint())
                .baseAmount(req.baseAmount())
                .deadline(req.deadline())
                .isActive(true)
                .build();
    }

    //Entity -> DTO
    public static MissionResDTO.AddMissionResDTO toAddMissionResDTO(Mission mission){
        return MissionResDTO.AddMissionResDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
