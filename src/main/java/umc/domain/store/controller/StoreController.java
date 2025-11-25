package umc.domain.store.controller;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.CreateReviewDto;
import umc.domain.store.dto.CreateStoreDto;
import umc.domain.store.service.StoreService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/api/v1/store")
@RequiredArgsConstructor
public class StoreController {

    private StoreService storeService;

    @PostMapping("/{memberId}")
    public ApiResponse<?> createStore(
            @NotBlank @PathVariable("memberId") Long memberId,
            @RequestBody @Valid CreateStoreDto createStoreDto
    ){


        Long storeId = storeService.createStore(createStoreDto, memberId);

        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code, storeId
        );


    }





}
