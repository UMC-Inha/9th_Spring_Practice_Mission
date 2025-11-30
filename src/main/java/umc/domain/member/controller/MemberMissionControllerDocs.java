package umc.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import umc.domain.member.dto.req.MemberMissionReqDTO;
import umc.domain.member.dto.res.MemberMissionResDTO;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.dto.PageResponseDTO;

public interface MemberMissionControllerDocs {
    @Operation(
            summary = "멤버의 진행중 미션 목록 조회 API",
            description = "특정 멤버의 진행중인 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ResponseEntity<ApiResponse<PageResponseDTO<MemberMissionResDTO.goingResDTO>>> getRunningMissions(
            Long memberId,
            MemberMissionReqDTO.FindMemberRunningMissionDTO dto
    );

    @Operation(
            summary = "멤버 미션 완료 처리 API",
            description = "특정 멤버의 진행 중인 미션을 완료 상태(SUCCESS)로 변경하고, 변경된 미션 정보를 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ResponseEntity<ApiResponse<MemberMissionResDTO.completeResDTO>> completeMission(
            Long memberMissionId
    );
}
