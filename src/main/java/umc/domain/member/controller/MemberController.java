package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.common.MemberCommandService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;


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
            @RequestBody MemberReqDTO.JoinDTO dto) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberCommandService.signUp(dto));
    }
}
