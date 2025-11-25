package umc.domain.store.service.command;

import org.springframework.transaction.annotation.Transactional;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;

public interface StoreCommandService {
    @Transactional
    StoreResDTO.CreateDTO createStore(Long ownerId, StoreReqDTO.CreateDTO request);
}
