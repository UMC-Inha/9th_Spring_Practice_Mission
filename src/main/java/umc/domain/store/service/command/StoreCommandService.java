package umc.domain.store.service.command;


import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;

public interface StoreCommandService {
    public StoreResDTO.Create createStore(
            StoreReqDTO.StoreCreate dto
    );
}
