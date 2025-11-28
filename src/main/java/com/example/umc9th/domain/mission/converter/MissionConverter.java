package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.Res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResDTO.MissionByStoreDTO toMissionByStore(Mission mission){
        return MissionResDTO.MissionByStoreDTO.builder()
                .missionId(mission.getId())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }


    public static MissionResDTO.MissionByStoreListDTO toMissionByStoreList(Page<Mission> missionPage){
        List<MissionResDTO.MissionByStoreDTO> missionList = missionPage.getContent().stream()
                .map(MissionConverter::toMissionByStore)
                .collect(Collectors.toList());

        return MissionResDTO.MissionByStoreListDTO.builder()
                .missionList(missionList)
                .listSize(missionPage.getSize())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
