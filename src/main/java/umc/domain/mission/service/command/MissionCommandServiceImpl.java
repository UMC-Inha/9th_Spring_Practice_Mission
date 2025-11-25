package umc.domain.mission.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Override
    public MissionResDTO.CreateDTO createMission(Long ownerId, Long storeId, MissionReqDTO.CreateDTO request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND_STORE));

        if (!store.getOwner().getId().equals(ownerId)) {
            throw new StoreException(StoreErrorCode.NOT_STORE_OWNER);
        }

        Mission mission = Mission.builder()
                .content(request.content())
                .point(request.point())
                .deadlineAt(request.deadlineAt())
                .store(store)
                .build();

        Mission saved = missionRepository.save(mission);

        return MissionResDTO.CreateDTO.from(saved);
    }
}
