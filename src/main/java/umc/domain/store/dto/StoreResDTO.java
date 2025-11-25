package umc.domain.store.dto;

import umc.domain.store.entity.Store;
import umc.domain.store.entity.StoreType;

public class StoreResDTO {

    public record CreateDTO(
            Long storeId,
            Long ownerId,
            String name,
            String address,
            StoreType type,
            Long districtId
    ) {
        public static CreateDTO from(Store store) {
            return new CreateDTO(
                    store.getId(),
                    store.getOwner().getId(),
                    store.getName(),
                    store.getAddress(),
                    store.getType(),
                    store.getDistrict().getId()
            );
        }
    }
}
