package umc.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.auth.dto.req.AuthReqDTO;
import umc.domain.auth.dto.res.AuthResDTO;
import umc.domain.auth.exception.code.AuthSuccessCode;
import umc.domain.auth.service.SignUpService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;
    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<AuthResDTO .SignUpDTO> signUp(
            @RequestBody AuthReqDTO.SignUpDTO dto
    ){
        return ApiResponse.onSuccess(AuthSuccessCode.SIGN_UP_SUCCESS, signUpService.signup(dto));
    }
}
