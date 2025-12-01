package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.converter.MissionCreateConverter;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
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
    public MissionResDTO.MissionDetailDTO createMission(Long storeId, MissionReqDTO.MissionCreateReq req){
        // 가게 존재 여부 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(StoreErrorCode.STORE_NOT_FOUND));

      Mission mission = MissionCreateConverter.toMission(req,store);

        //  저장
        Mission saved = missionRepository.save(mission);

        // DTO로 변환 후 반환
        return MissionCreateConverter.toMissionDetailDTO(saved);
    }
}
