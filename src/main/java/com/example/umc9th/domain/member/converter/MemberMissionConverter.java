package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    // 엔티티 -> DTO
    public static MemberMissionResDTO.MissionByStatusDTO toMissionByStatusDTO(MemberMission memberMission) {
        return MemberMissionResDTO.MissionByStatusDTO.builder()
                .memberMissionId(memberMission.getId())
                .deadline(memberMission.getMission().getDeadline())
                .point(memberMission.getMission().getPoint())
                .isComplete(memberMission.isStatus())
                .storeName(memberMission.getMission().getStore().getName())
                .build();
    }

    // DTO -> 엔티티
    public static MemberMissionResDTO.MissionByStatusListDTO toMissionByStatusListDTO(Page<MemberMission> memberMissionPage) {
        List<MemberMissionResDTO.MissionByStatusDTO> missionList =
                memberMissionPage.getContent().stream().map(MemberMissionConverter::toMissionByStatusDTO).toList();

        return MemberMissionResDTO.MissionByStatusListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}
