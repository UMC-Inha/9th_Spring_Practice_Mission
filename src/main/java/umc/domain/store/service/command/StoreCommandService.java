package umc.domain.store.service.command;

import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;

public interface StoreCommandService {
    StoreResDTO.AddStoreResDTO addStoreToRegion(Long regionId, StoreReqDTO.AddStoreDTO req);
}
