package umc.domain.store.dto;

import jakarta.validation.constraints.NotBlank;

public class StoreReqDTO {

    public record createStoreReqDTO(
            @NotBlank
            String name,
            @NotBlank
            String type,
            @NotBlank
            String address
    ) {
    }
}
