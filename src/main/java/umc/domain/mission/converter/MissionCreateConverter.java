package umc.domain.mission.converter;

import umc.domain.mission.dto.req.MissionCreateReqDTO;
import umc.domain.mission.dto.res.MissionDetailDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Store;

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
    //MissionCreateReqDTO -> Mission 엔티티
    public  static Mission  toMission(MissionCreateReqDTO.MissionCreateReq req, Store store) {
        return Mission.builder()
                .deadline(req.getDeadline())
                .conditional(req.getConditional())
                .point(req.getPoint())
                .leastAmount(req.getLeastAmount())
                .store(store)
                .build();
    }

}
