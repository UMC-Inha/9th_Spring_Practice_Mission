package umc.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.command.ReviewCommandService;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.exception.store.code.StoreSuccessCode;
import umc.domain.store.service.command.StoreCommandService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final ReviewCommandService reviewCommandService;
    private final StoreCommandService storeCommandService;

    @PostMapping("/{region_id}")
    public ApiResponse<StoreResDTO.createStoreResDTO> createStore(
            @RequestParam Long regionId,
            @RequestBody StoreReqDTO.createStoreReqDTO reqDTO
    ) {
        StoreResDTO.createStoreResDTO resDTO = storeCommandService.createStore(regionId, reqDTO);
        return ApiResponse.onSuccess(StoreSuccessCode.CREATED, resDTO);
    }

    @PostMapping("/{storeId}/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewCreateDTO> createReview(
            @PathVariable Long storeId,
            @PathVariable Long memberId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreateDTO reviewCreateDTO
    ) {

        ReviewResDTO.ReviewCreateDTO dto = reviewCommandService.createReview(storeId, memberId, reviewCreateDTO);
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, dto);
    }
}
