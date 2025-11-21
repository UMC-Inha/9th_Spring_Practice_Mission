package umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.domain.mission.dto.req.MissionReqDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    //DTO -> Entity
    public static Mission toMission(MissionReqDTO.AddMissionReqDTO req, Store store
                                    ){
        return Mission.builder()
                .store(store)
                .rewardPoint(req.rewardPoint())
                .baseAmount(req.baseAmount())
                .deadline(req.deadline())
                .isActive(true)
                .build();
    }

    //Entity -> DTO
    public static MissionResDTO.AddMissionResDTO toAddMissionResDTO(Mission mission){
        return MissionResDTO.AddMissionResDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    //Mission Entity -> MissionByStoreResDTO
    public static MissionResDTO.MissionByStoreResDTO toMissionByStoreResDTO(Mission mission){
        return MissionResDTO.MissionByStoreResDTO.builder()
                .missionId(mission.getId())
                .rewardPoint(mission.getRewardPoint())
                .baseAmount(mission.getBaseAmount())
                .deadline(mission.getDeadline())
                .isActive(mission.getIsActive())
                .build();
    }

    //Page<Mission> -> MissionByStoreListResDTO
    public static MissionResDTO.MissionByStoreListResDTO toMissionByStoreListResDTO(Page<Mission> missionPage){
        List<MissionResDTO.MissionByStoreResDTO> missionList = missionPage.getContent().stream()
                .map(MissionConverter::toMissionByStoreResDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionByStoreListResDTO.builder()
                .missionList(missionList)
                .listSize(missionPage.getSize())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();

    }
}
