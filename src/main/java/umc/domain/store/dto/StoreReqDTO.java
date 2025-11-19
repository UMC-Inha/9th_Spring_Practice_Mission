package umc.domain.store.dto;

import jakarta.validation.constraints.NotNull;

public class StoreReqDTO {

    public record createStoreReqDTO(
            @NotNull
            String name,
            @NotNull
            String type,
            @NotNull
            String address
    ) {
    }
}
