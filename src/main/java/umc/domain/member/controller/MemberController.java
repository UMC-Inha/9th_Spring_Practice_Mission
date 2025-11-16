package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.code.MemberSuccessCode;
import umc.domain.member.dto.res.MemberResponseDTO;
import umc.domain.member.service.MemberQueryService;
import umc.domain.mission.code.MissionSuccessCode;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.dto.PageResponseDTO;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService memberQueryService;

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
}
