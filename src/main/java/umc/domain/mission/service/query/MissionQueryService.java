package umc.domain.mission.service.query;

import org.springframework.data.domain.Pageable;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.global.apiPayload.dto.PageResponseDTO;

public interface MissionQueryService {
    PageResponseDTO<MissionResDTO.MissionResponseDTO> getAvailableMissions(Long regionId, Long memberId, Pageable pageable);
    PageResponseDTO<MissionResDTO.StoreMissionDTO> getStoreMissions(Long storeId, Integer pageable);
}
