package com.example.umc9th.domain.store.service;

import com.example.umc9th.domain.store.converter.StoreCreateConverter;
import com.example.umc9th.domain.store.dto.StoreCreateReqDTO;
import com.example.umc9th.domain.store.dto.StoreCreateResDTO;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.RegionErrorCode;
import com.example.umc9th.domain.store.exception.RegionException;
import com.example.umc9th.domain.store.repository.RegionRepository;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional

public class StoreCreateService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    public StoreCreateResDTO createStore(StoreCreateReqDTO storeCreateReqDTO, Long regionId) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionException(RegionErrorCode.REGION_NOT_FOUND));

        Store store = StoreCreateConverter.toStore(storeCreateReqDTO, region);

        storeRepository.save(store);

        return StoreCreateConverter.toResponse(store);
    }

}
