package umc.domain.mission.converter;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import umc.domain.mission.dto.MemberMissionResDTO;
import umc.domain.mission.entity.MemberMission;
import umc.domain.mission.entity.Mission;

public class MemberMissionConverter {

    public static MemberMissionResDTO.SearchListDTO toSearchListDTO(Page<MemberMission> page) {

        return MemberMissionResDTO.SearchListDTO.builder()
                .missionList(
                        page.getContent().stream()
                                .map(MemberMissionConverter::toSearchDTO)
                                .collect(Collectors.toList())
                )
                .listSize(page.getNumberOfElements())      // 현재 페이지에 포함된 미션 수
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

    public static MemberMissionResDTO.SearchDTO toSearchDTO(MemberMission memberMission) {

        Mission mission = memberMission.getMission(); // 연관 엔티티

        return MemberMissionResDTO.SearchDTO.builder()
                .missionId(mission.getId())
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadlineAt(mission.getDeadlineAt())
                .acceptedAt(memberMission.getAcceptedAt())
                .successAt(memberMission.getSuccessAt())
                .status(memberMission.getStatus())
                .build();
    }
}
