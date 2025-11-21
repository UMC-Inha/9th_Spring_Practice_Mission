package umc.domain.mission.service.query;

import umc.domain.mission.dto.res.MissionResDTO;

public interface MissionQueryService {
    MissionResDTO.MissionByStoreListResDTO getMissionByStoreListResDTO(Long storeId, Integer page);
}
