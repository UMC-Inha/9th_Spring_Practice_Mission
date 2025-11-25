package umc.domain.misson.service.query;

import umc.domain.misson.dto.MissionResDTO;

public interface MissionQueryService {

    MissionResDTO.MissionPreviewListDTO findMissionsByStore(
            String storeName,
            Integer page
    );

    MissionResDTO.MissionPreviewListDTO findOngoingMissions(
            Long memberId,
            Integer page
    );
}
