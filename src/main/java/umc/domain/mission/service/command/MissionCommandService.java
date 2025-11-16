package umc.domain.mission.service.command;

import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;

public interface MissionCommandService {

    MissionResDTO.CreateDTO createMission(Long ownerId, Long storeId, MissionReqDTO.CreateDTO request);
}
