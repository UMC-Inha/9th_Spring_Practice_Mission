package umc.domain.store.dto;

public class StoreReqDTO {

    public record createStoreReqDTO(
            String name,
            String type,
            String address
    ) {
    }
}
