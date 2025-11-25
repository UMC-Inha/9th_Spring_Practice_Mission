package umc.domain.misson.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.misson.converter.MissionConverter;
import umc.domain.misson.dto.MissionReqDTO;
import umc.domain.misson.dto.MissionResDTO;
import umc.domain.misson.entity.Mission;
import umc.domain.misson.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.store.StoreException;
import umc.domain.store.exception.store.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;
import umc.global.util.RandomCodeGenerator;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public MissionResDTO.createMissionResDTO createMission(
            Long storeId,
            MissionReqDTO.createMissionReqDTO reqDTO
    ) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        Mission mission = MissionConverter.toMission(store, reqDTO, RandomCodeGenerator.generateRandomInt());

        missionRepository.save(mission);
        return MissionConverter.toCreateMissionResDTO(mission);
    }
}
