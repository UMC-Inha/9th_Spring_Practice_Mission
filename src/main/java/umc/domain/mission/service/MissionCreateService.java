package umc.domain.mission.service;

import umc.domain.mission.dto.req.MissionCreateReqDTO;
import umc.domain.mission.dto.res.MissionDetailDTO;

public interface MissionCreateService {
    MissionDetailDTO createMission(Long storeId, MissionCreateReqDTO.MissionCreateReq req);
}
