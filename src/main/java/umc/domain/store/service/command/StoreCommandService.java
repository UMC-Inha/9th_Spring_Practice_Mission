package umc.domain.store.service.command;

import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;

public interface StoreCommandService {

    StoreResDTO.createStoreResDTO createStore(
            Long regionId,
            StoreReqDTO.createStoreReqDTO reqDTO
    );
}
