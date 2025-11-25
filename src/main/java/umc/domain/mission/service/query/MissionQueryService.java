package umc.domain.mission.service.query;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.GetMissionResponse;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    public List<GetMissionResponse> getAvailableMissions(
            Long userId,
            String address,
            LocalDateTime cursorDeadline,
            Long cursorMissionId,
            int limit
    ) {
        return missionRepository.findAvailableMissionsNative(
                        userId,
                        address,
                        cursorDeadline,
                        cursorMissionId,
                        limit
                ).stream()
                .map(row -> new GetMissionResponse(
                        row.getMissionId(),
                        row.getContent(),
                        row.getPoint(),
                        row.getDeadline(),
                        row.getStoreName(),
                        row.getStoreType()
                ))
                .toList();
    }

    public MissionResDTO.SearchListDTO getMission(Long storeId, Integer page){

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND_STORE));

        PageRequest pageRequest = PageRequest.of(page - 1, 5);
        Page<Mission> missions = missionRepository.findByStore(store, pageRequest);

        return MissionConverter.toSearchListDTO(missions);
    }
}
