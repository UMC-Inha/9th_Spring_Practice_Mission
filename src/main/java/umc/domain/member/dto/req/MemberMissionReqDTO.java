package umc.domain.member.dto.req;


public class MemberMissionReqDTO {

    public record createReqDTO(
            Long memberId,
            Long missionId
    ){}
}
