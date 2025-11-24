package umc.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.location.entity.Location;
import umc.domain.location.repository.LocationRepository;
import umc.domain.store.converter.StoreConverter;
import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;
import umc.domain.store.entity.Store;
import umc.domain.store.repository.StoreRepository;
import umc.global.apiPayload.Exception.GeneralException;
import umc.global.apiPayload.code.GeneralErrorCode;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements  StoreCommandService{

    private final LocationRepository locationRepository;
    private final StoreRepository storeRepository;

    @Override
    public StoreResDTO.StoreInfo addStoreToLocation(Long locationId, StoreReqDTO.CreateStore req) {

        // 1) 지역 조회
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        // 2) Store 생성 + 지역 연결
        Store store = StoreConverter.toStore(req, location);

        // 3) 저장
        Store saved = storeRepository.save(store);

        // 4) 응답 DTO 변환
        return StoreConverter.toStoreInfo(saved);
    }

}
