package umc.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.user.dto.req.UserMissionReqDTO;
import umc.domain.user.dto.res.UserMissionResDTO;
import umc.domain.user.service.command.UserMissionCommandService;
import umc.domain.user.service.query.UserMissionQueryService;
import umc.global.annotation.CheckPage;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/user-mission")
public class UserMissionController {
    private final UserMissionCommandService userMissionCommandService;
    private final UserMissionQueryService userMissionQueryService;

    @PostMapping("/")
    public ApiResponse<UserMissionResDTO.JoinMissionResDTO> joinMission(
            @RequestBody @Valid UserMissionReqDTO.JoinMissionReqDTO req
            ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                userMissionCommandService.joinMission(req)
        );
    }

    @GetMapping("/my/in-progress")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "내가 진행 중인 미션 목록을 조회합니다. page는 1부터 시작합니다")
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호 (1 이상)")
    })
    public ApiResponse<UserMissionResDTO.MyMissionByStatusListResDTO>  getMyInProgressMissionList(
            @CheckPage @RequestParam(name="page") Integer page
    ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                userMissionQueryService.getMyInProgressMissionList(page)
        );
    }

}
