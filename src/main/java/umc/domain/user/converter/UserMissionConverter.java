package umc.domain.user.converter;

import org.springframework.data.domain.Page;
import umc.domain.mission.entity.Mission;
import umc.domain.user.dto.req.UserMissionReqDTO;
import umc.domain.user.dto.res.UserMissionResDTO;
import umc.domain.user.entity.User;
import umc.domain.user.enums.MissionStatus;
import umc.domain.user.mapping.UserMission;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

public class UserMissionConverter {

    //DTO -> Entity
    public static UserMission toUserMission(User user, Mission mission){
        return UserMission.builder()
                .user(user)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();
    }
    //Entity -> DTO
    public static UserMissionResDTO.JoinMissionResDTO toJoinMissionResDTO(UserMission userMission){
        return UserMissionResDTO.JoinMissionResDTO.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .status(userMission.getStatus())
                .createdAt(userMission.getCreatedAt())
                .build();
    }

    //UserMission 엔티티 -> MyMissionByStatusResDTO
    public static UserMissionResDTO.MyMissionByStatusResDTO  toMyMissionByStatusResDTO(UserMission userMission){
        return UserMissionResDTO.MyMissionByStatusResDTO.builder()
                .userMissionId(userMission.getId())
                .storeName(userMission.getMission().getStore().getName())
                .rewardPoint(userMission.getMission().getRewardPoint())
                .baseAmount(userMission.getMission().getBaseAmount())
                .deadLine(userMission.getMission().getDeadline())
                .status(userMission.getStatus())
                .build();
    }

    //Page<UserMission> -> MyMissionByStatusListDTO
    public static UserMissionResDTO.MyMissionByStatusListResDTO toMyMissionByStatusListResDTO(Page<UserMission> missionPage){
        List<UserMissionResDTO.MyMissionByStatusResDTO> missionList = missionPage.getContent().stream()
                .map(UserMissionConverter::toMyMissionByStatusResDTO)
                .collect(Collectors.toList());

        return UserMissionResDTO.MyMissionByStatusListResDTO.builder()
                .missionList(missionList)
                .listSize(missionPage.getSize())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
