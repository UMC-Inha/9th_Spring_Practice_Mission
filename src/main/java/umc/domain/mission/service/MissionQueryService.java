package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.mission.code.MissionErrorCode;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResponseDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.exception.MissionException;
import umc.domain.mission.repository.MissionRepository;
import umc.global.apiPayload.dto.PageResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionQueryService {
    private final MissionRepository missionRepository;

    public PageResponseDTO<MissionResponseDTO> getAvailableMissions(Long regionId, Long memberId, Pageable pageable) {
        Page<Mission> missions = missionRepository.findAvailableMissionsByRegion(regionId, memberId, pageable);

        if (missions.isEmpty()) {
            throw new MissionException(MissionErrorCode.MISSION_NOT_FOUND);
        }

        List<MissionResponseDTO> list = missions
                .map(MissionConverter::toMissionResponseDTO)
                .getContent();

        return new PageResponseDTO<>(
                list,
                missions.getNumber(),
                missions.getSize(),
                missions.getTotalElements(),
                missions.getTotalPages(),
                missions.hasNext()
        );
    }
}
