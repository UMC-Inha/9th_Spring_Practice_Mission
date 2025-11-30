package umc.domain.member.dto.req;


import umc.global.annotation.ValidPage;

public class MemberMissionReqDTO {

    public record createReqDTO(
            Long memberId,
            Long missionId
    ){}

    public record FindMemberRunningMissionDTO(
            @ValidPage
            Integer page
    ){
        public FindMemberRunningMissionDTO {
            if (page == null) page = 1;
        }
    }
}
