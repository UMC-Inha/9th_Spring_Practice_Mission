package umc.domain.home.dto;

import lombok.Builder;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.mission.dto.MissionResDTO;

import java.util.List;

public class HomeResDTO {

    @Builder
    public record HomeInfoDTO(
            MemberResDTO.MyPageMemberDTO memberInfo,    // 멤버의 포인트가 필요 -> 이후 확장 고려해서 myPage DTO 재사용
            int completedMissionRate,
            List<MissionResDTO.SimpleMissionDTO> availableMissions
    ) {}
}
