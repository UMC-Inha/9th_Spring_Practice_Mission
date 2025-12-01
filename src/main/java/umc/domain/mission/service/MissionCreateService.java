package umc.domain.mission.service;

import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;

public interface MissionCreateService {
    MissionResDTO.MissionDetailDTO createMission(Long storeId, MissionReqDTO.MissionCreateReq req);
}
