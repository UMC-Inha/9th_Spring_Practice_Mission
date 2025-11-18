package umc.domain.store.dto.res;

import lombok.Builder;
import lombok.Getter;

public class StoreResDTO {

    @Getter
    @Builder
    public static class StoreInfo{
        private Long storeId;
        private String name;
        private Long managerNumber;
        private String detailAddress;
        private String locationName;
    }
}
