package umc.domain.store.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.store.converter.StoreConverter;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.entity.Region;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.region.RegionException;
import umc.domain.store.exception.region.code.RegionErrorCode;
import umc.domain.store.repository.RegionRepository;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final RegionRepository regionRepository;
    private final StoreRepository storeRepository;

    @Override
    public StoreResDTO.createStoreResDTO createStore(
            Long regionId,
            StoreReqDTO.createStoreReqDTO reqDTO
    ) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionException(RegionErrorCode.NOT_FOUND));
        Store store = StoreConverter.toStore(region, reqDTO);

        storeRepository.save(store);
        return StoreConverter.convertToStoreResDTO(store, region);
    }
}
