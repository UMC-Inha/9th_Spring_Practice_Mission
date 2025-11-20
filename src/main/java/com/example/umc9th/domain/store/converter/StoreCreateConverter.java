package com.example.umc9th.domain.store.converter;

import com.example.umc9th.domain.store.dto.StoreCreateReqDTO;
import com.example.umc9th.domain.store.dto.StoreCreateResDTO;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import lombok.Builder;

@Builder
public class StoreCreateConverter {

    public static Store toStore(StoreCreateReqDTO request, Region region) {
        return Store.builder()
                .name(request.getStoreName())
                .managerNumber(request.getManagerNumber())
                .detailAddress(request.getDetailAddress())
                .region(region)
                .build();
    }

    public static StoreCreateResDTO toResponse(Store store) {
        return StoreCreateResDTO.builder()
                .StoreId(store.getId())
                .StoreName(store.getName())
                .ManagerNumber(store.getManagerNumber())
                .detailAddress(store.getDetailAddress())
                .regionName(store.getRegion().getName())
                .build();
    }
}
