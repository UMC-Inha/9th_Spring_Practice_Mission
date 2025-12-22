package umc.domain.store.converter;

import umc.domain.Region.entity.Region;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.entity.Store;

public class StoreConverter {

    //StoreReqDTO.Create -> Store
    public static Store toStore(StoreReqDTO.StoreCreate dto, Region region) {
        return Store.builder()
                .name(dto.name())
                .ownerNumber(dto.ownerNumber())
                .detailAddress(dto.detailAddress())
                .region(region)
                .build();
    }

    //Store -> StoreResDTO.Create
    public static StoreResDTO.Create toStoreResDTO(Store store) {
        return StoreResDTO.Create.builder()
                .name(store.getName())
                .ownerNumber(store.getOwnerNumber())
                .detailAddress(store.getDetailAddress())
                .regionId(store.getRegion().getId())
                .build();
    }

}
