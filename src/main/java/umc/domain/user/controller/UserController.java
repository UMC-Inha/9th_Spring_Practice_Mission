package umc.domain.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.user.dto.req.UserReqDTO;
import umc.domain.user.dto.res.UserResDTO;
import umc.domain.user.exception.code.UserSuccessCode;
import umc.domain.user.service.command.UserCommandService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserCommandService userCommandService;
    //회원가입 API
    @PostMapping("/sign-up")
    public ApiResponse<UserResDTO.JoinDTO> signUp(@RequestBody @Valid UserReqDTO.JoinDto joinDto){
        return ApiResponse.onSuccess(UserSuccessCode.FOUND, userCommandService.signup(joinDto));
    }
}
