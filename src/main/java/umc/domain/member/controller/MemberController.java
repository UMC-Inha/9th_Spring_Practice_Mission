package umc.domain.member.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dtos.MemberReqDTO;
import umc.domain.member.dtos.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberCommandService;
import umc.domain.member.service.MemberQueryService;
import umc.domain.mission.controller.MissionControllerDocs;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;
import umc.global.auth.enums.Role;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signup(dto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}