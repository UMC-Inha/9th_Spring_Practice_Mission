package umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import umc.domain.mission.dto.MemberMissionResDTO.SearchListDTO;
import umc.domain.mission.entity.MissionStatus;
import umc.global.annotation.ValidPage;
import umc.global.apiPayload.ApiResponse;

public interface MemberMissionControllerDocs {

    @Operation(
            summary = "멤버의 미션 조회",
            description = """
                    특정 멤버의 미션을 상태(status) 기반으로 조회합니다.
                    
                    ### 제공되는 정보
                    - 미션 정보(content, point, deadlineAt)
                    - 멤버가 미션을 수락한 시간(acceptedAt), 성공 시간(successAt)
                    - MissionStatus(IN_PROGRESS, SUCCESS, FAIL 등) 필터링 가능
                    - page 값은 1부터 시작합니다.
                    - status 파라미터를 생략하면 전체 미션 조회
                    
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "미션 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "page 검증 실패 또는 status 바인딩 실패"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "멤버를 찾을 수 없음")
    })
    ApiResponse<SearchListDTO> getMemberMissions(Long memberId, MissionStatus status, @ValidPage Integer page);


}
