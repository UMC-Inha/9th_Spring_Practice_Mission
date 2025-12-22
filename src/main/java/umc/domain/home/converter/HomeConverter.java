package umc.domain.home.converter;



import umc.domain.home.dto.HomeResDTO;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;

import java.util.List;

public class HomeConverter {

    public static HomeResDTO.HomeInfoDTO toHomeInfoDTO(
            Member member,
            int completedMissionRate,
            List<Mission> availableMissions
    ) {

        //Member -> MyPageMemberDTO
        MemberResDTO.MyPageMemberDTO memberInfo = MemberConverter.toMyPageMemberDTO(member);

        //Mission 리스트 -> SimpleMissionDTO 리스트
        List<MissionResDTO.SimpleMissionDTO> missionDTOs = availableMissions.stream()
                .map(MissionConverter::toSimpleMissionDTO)
                .toList();

        //HomeInfoDTO
        return HomeResDTO.HomeInfoDTO.builder()
                .memberInfo(memberInfo)
                .completedMissionRate(completedMissionRate)
                .availableMissions(missionDTOs)
                .build();
    }
}
