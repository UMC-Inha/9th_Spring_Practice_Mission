package umc.domain.store.service;

import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;

public interface StoreCommandService {
    StoreResDTO.StoreInfo addStoreToLocation(Long locationId, StoreReqDTO.CreateStore req);
}
