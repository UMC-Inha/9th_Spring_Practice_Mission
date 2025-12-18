package umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.common.MemberCommandService;
import umc.domain.member.service.query.MemberQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @DeleteMapping("/{memberId}/hard")
    ApiResponse<Void> hardDelete(
            @PathVariable Long memberId,
            @RequestParam(name = "hard", defaultValue = "false") boolean hard
    ) {

        memberCommandService.deleteMember(memberId, hard);

        GeneralSuccessCode code = GeneralSuccessCode.NO_CONTENT;

        return ApiResponse.onSuccess(code);
    }

    @PostMapping("/sign-up")
    ApiResponse<MemberResDTO.JoinDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberCommandService.signUp(dto));
    }

    @PostMapping("/login")
    ApiResponse<MemberResDTO.LoginDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberQueryService.login(dto));
    }
}
