package umc.domain.user.service.command;

import umc.domain.user.dto.req.UserMissionReqDTO;
import umc.domain.user.dto.res.UserMissionResDTO;

public interface UserMissionCommandService {
    UserMissionResDTO.JoinMissionResDTO joinMission(UserMissionReqDTO.JoinMissionReqDTO req);
}
