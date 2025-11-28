package umc.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record createStoreResDTO(
            Long storeId,
            Long regionId,
            String regionName,
            String name,
            String type,
            String address
    ) {
    }

}
