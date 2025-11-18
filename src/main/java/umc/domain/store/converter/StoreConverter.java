package umc.domain.store.converter;

import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.entity.Region;
import umc.domain.store.entity.Store;

public class StoreConverter {

    public static Store toStore(Region region, StoreReqDTO.createStoreReqDTO reqDTO) {
        return Store.builder()
                .name(reqDTO.name())
                .type(reqDTO.type())
                .address(reqDTO.address())
                .region(region)
                .build();
    }

    public static StoreResDTO.createStoreResDTO convertToStoreResDTO(Store store, Region region) {
        return StoreResDTO.createStoreResDTO.builder()
                .storeId(store.getId())
                .regionId(region.getId())
                .regionName(region.getName())
                .name(store.getName())
                .type(store.getType())
                .address(store.getAddress())
                .build();
    }
}
