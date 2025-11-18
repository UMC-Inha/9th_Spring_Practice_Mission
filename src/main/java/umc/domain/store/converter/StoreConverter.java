package umc.domain.store.converter;

import umc.domain.location.entity.Location;
import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;
import umc.domain.store.entity.Store;

public class StoreConverter {
    // DTO -> 엔티티
    public static Store toStore(StoreReqDTO.CreateStore req, Location location) {
        return Store.builder()
                .name(req.getName())
                .managerNumber(req.getManagerNumber())
                .detailAddress(req.getDetailAddress())
                .location(location)
                .build();
    }

    // 엔티티 -> DTO
    public static StoreResDTO.StoreInfo toStoreInfo(Store store) {
        return StoreResDTO.StoreInfo.builder()
                .storeId(store.getId())
                .name(store.getName())
                .managerNumber(store.getManagerNumber())
                .detailAddress(store.getDetailAddress())
                .locationName(store.getLocation().getName())
                .build();
    }
}
