package umc.domain.store.dto.res;

import lombok.Builder;
import lombok.Getter;

public class StoreResDTO {

    @Builder
    public record StoreInfo(
        Long storeId,
        String name,
        Long managerNumber,
        String detailAddress,
        String locationName
    ){}
}
