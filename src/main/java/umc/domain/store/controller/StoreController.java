package umc.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;
import umc.domain.store.exception.code.StoreSuccessCode;
import umc.domain.store.service.command.StoreCommandService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/regions/{regionId}/stores")
public class StoreController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResDTO.AddStoreResDTO> addStore(
            @PathVariable Long regionId,
            @RequestBody @Valid StoreReqDTO.AddStoreDTO req
            ){
        return ApiResponse.onSuccess(
                StoreSuccessCode.STORE_CREATED,
                storeCommandService.addStoreToRegion(regionId,req)
        );
    }
}
