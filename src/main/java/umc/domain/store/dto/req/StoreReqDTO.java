package umc.domain.store.dto.req;

import lombok.Builder;
import lombok.Getter;

public class StoreReqDTO {

    @Getter
    @Builder
    public static class CreateStore {
        private String name;
        private Long managerNumber;
        private String detailAddress;
    }
}
