package umc.domain.mission.service;

import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionDetailDTO;

public interface MissionCreateService {
    MissionDetailDTO createMission(Long storeId, MissionReqDTO.MissionCreateReq req);
}
