package umc.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.code.MissionErrorCode;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.exception.MissionException;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.code.StoreErrorCode;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.repository.StoreRepository;
import umc.global.apiPayload.dto.PageResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService{

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public PageResponseDTO<MissionResDTO.MissionResponseDTO> getAvailableMissions(Long regionId, Long memberId, Pageable pageable) {
        Page<Mission> missions = missionRepository.findAvailableMissionsByRegion(regionId, memberId, pageable);

        if (missions.isEmpty()) {
            throw new MissionException(MissionErrorCode.MISSION_NOT_FOUND);
        }

        List<MissionResDTO.MissionResponseDTO> list = missions
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

    @Override
    public PageResponseDTO<MissionResDTO.StoreMissionDTO> getStoreMissions(Long storeId, Integer page) {

        // 1. storeId 존재 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 2. 페이징 설정 (size=10 고정)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 3. store의 mission 목록 조회
        Page<Mission> missions = missionRepository.findAllByStore(store, pageRequest);

        if (missions.isEmpty()) {
            throw new MissionException(MissionErrorCode.MISSION_NOT_FOUND);
        }

        // 4. DTO 변환
        List<MissionResDTO.StoreMissionDTO> list = missions
                .map(MissionConverter::toStoreMissionDTO)
                .getContent();

        // 5. 응답 반환
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
