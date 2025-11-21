package umc.domain.mission.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public MissionResDTO.MissionByStoreListResDTO getMissionByStoreListResDTO(Long storeId, Integer page){

        Store store = storeRepository.findById(storeId).orElseThrow(()-> new StoreException(StoreErrorCode.NOT_FOUND));
        PageRequest pageRequest = PageRequest.of(page-1,10);
        Page<Mission> missionPage = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toMissionByStoreListResDTO(missionPage);

    }
}
