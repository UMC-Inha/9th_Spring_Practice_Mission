package umc.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;
import umc.domain.store.service.StoreCommandService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/locations")
public class StoreController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/{locationId}/stores")
    public ApiResponse<StoreResDTO.StoreInfo> addStore(
            @PathVariable Long locationId,
            @RequestBody StoreReqDTO.CreateStore req
    ) {
        StoreResDTO.StoreInfo result = storeCommandService.addStoreToLocation(locationId, req);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
