package umc.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.user.dto.req.UserMissionReqDTO;
import umc.domain.user.dto.res.UserMissionResDTO;
import umc.domain.user.service.command.UserMissionCommandService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-mission")
public class UserMissionController {
    private final UserMissionCommandService userMissionCommandService;

    @PostMapping("/")
    public ApiResponse<UserMissionResDTO.JoinMissionResDTO> joinMission(
            @RequestBody @Valid UserMissionReqDTO.JoinMissionReqDTO req
            ){
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                userMissionCommandService.joinMission(req)
        );
    }
}
