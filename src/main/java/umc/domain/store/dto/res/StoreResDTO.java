package umc.domain.store.dto.res;

import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record CreateResDTO(
            Long storeId
    ){}
}
