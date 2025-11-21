package umc.domain.mission.service.command;

import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;


public interface MissionCommandService {
    MissionResDTO.CreateResDTO createMission(MissionReqDTO.CreateReqDTO dto);
}
