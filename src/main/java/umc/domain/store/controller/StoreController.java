package umc.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.store.dto.req.StoreReqDTO;
import umc.domain.store.dto.res.StoreResDTO;
import umc.domain.store.code.StoreSuccessCode;
import umc.domain.store.service.command.StoreCommandService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<StoreResDTO.CreateResDTO>> create(
            @RequestBody @Valid StoreReqDTO.CreateRequestDTO dto
    ){
        return ResponseEntity.ok(
                ApiResponse.onSuccess(StoreSuccessCode.STORE_CREATE_SUCCESS, storeCommandService.create(dto))
        );
    }
}
