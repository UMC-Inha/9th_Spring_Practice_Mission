package umc.domain.store.service.command;


import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;

public interface StoreCommandService {
    StoreResDTO.CreateResDTO create(StoreReqDTO.CreateRequestDTO dto);
}
