package umc.domain.store.dto;

public class StoreReqDTO {

    public record StoreCreate(
        String name,
        String ownerNumber,
        Long regionId,
        String detailAddress
    ){}
}
