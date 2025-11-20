package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.converter.MissionCreateConverter;
import umc.domain.mission.dto.req.MissionCreateReqDTO;
import umc.domain.mission.dto.res.MissionDetailDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;
import umc.global.apiPayload.Exception.GeneralException;

@Service
@RequiredArgsConstructor
public class MissionCreateServiceImpl implements MissionCreateService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public MissionDetailDTO createMission(Long storeId, MissionCreateReqDTO.MissionCreateReq req){
        // 가게 존재 여부 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(StoreErrorCode.STORE_NOT_FOUND));

        // Mission 엔티티 생성
        Mission mission = Mission.builder()
                .deadline(req.getDeadline())
                .conditional(req.getConditional())
                .point(req.getPoint())
                .leastAmount(req.getLeastAmount())
                .store(store)
                .build();

        //  저장
        Mission saved = missionRepository.save(mission);

        // DTO로 변환 후 반환
        return MissionCreateConverter.toMissionDetailDTO(saved);
    }
}
