package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.store.dto.StoreCreateReqDTO;
import com.example.umc9th.domain.store.dto.StoreCreateResDTO;
import com.example.umc9th.domain.store.service.StoreCreateService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreCreateController {
    private final StoreCreateService storeCreateService;

    @PostMapping("/{RegionId}")
    public ResponseEntity<ApiResponse<StoreCreateResDTO>> createStore(
            @PathVariable Long regionId,
            @RequestBody StoreCreateReqDTO req) {

        StoreCreateResDTO result = storeCreateService.createStore(req,regionId);
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, result));
    }
}
