package umc.domain.user.converter;

import umc.domain.mission.entity.Mission;
import umc.domain.user.dto.req.UserMissionReqDTO;
import umc.domain.user.dto.res.UserMissionResDTO;
import umc.domain.user.entity.User;
import umc.domain.user.enums.MissionStatus;
import umc.domain.user.mapping.UserMission;

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
}
