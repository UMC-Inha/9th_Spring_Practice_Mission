package umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.code.MemberSuccessCode;
import umc.domain.member.dto.req.MemberReqDTO;
import umc.domain.member.dto.res.MemberResponseDTO;
import umc.domain.member.service.command.MemberCommandService;
import umc.domain.member.service.query.MemberQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.dto.PageResponseDTO;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDTO<MemberResponseDTO.SearchDTO>>> getMembers(
            @PageableDefault(
                    page = 0,
                    size = 10,
                    sort = "id",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable
    ) {
        PageResponseDTO<MemberResponseDTO.SearchDTO> result =
                memberQueryService.getMembers(pageable);

        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberSuccessCode.MEMBER_LIST_SUCCESS, result)
        );
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<MemberResponseDTO.SignupResponseDTO>> signUp(

            @RequestBody @Valid MemberReqDTO.SignupRequestDTO dto
            ){
        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberSuccessCode.MEMBER_SIGNUP_SUCCESS, memberCommandService.signup(dto))
        );
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberResponseDTO.LoginDTO>> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO dto
    ){
        return ResponseEntity.ok(
                ApiResponse.onSuccess(MemberSuccessCode.MEMBER_LOGIN_FOUND, memberQueryService.login(dto))
        );
    }
}
