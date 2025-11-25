package umc.domain.misson.service.command;

import umc.domain.misson.dto.MissionReqDTO;
import umc.domain.misson.dto.MissionResDTO;

public interface MissionCommandService {

    MissionResDTO.createMissionResDTO createMission(
            Long storeId,
            MissionReqDTO.createMissionReqDTO reqDTO
    );

    MissionResDTO.MissionPreviewDTO updateMissionStatus(
            Long memberId,
            Long missionId
    );
}
