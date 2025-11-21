package umc.domain.user.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.exception.MissionException;
import umc.domain.mission.exception.code.MissionErrorCode;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.user.converter.UserMissionConverter;
import umc.domain.user.dto.req.UserMissionReqDTO;
import umc.domain.user.dto.res.UserMissionResDTO;
import umc.domain.user.entity.User;
import umc.domain.user.enums.MissionStatus;
import umc.domain.user.exception.UserException;
import umc.domain.user.exception.code.UserErrorCode;
import umc.domain.user.mapping.UserMission;
import umc.domain.user.repository.UserMissionRepository;
import umc.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserMissionCommandServiceImpl implements UserMissionCommandService{
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;

    @Override
    public UserMissionResDTO.JoinMissionResDTO joinMission(UserMissionReqDTO.JoinMissionReqDTO req){
        User user = userRepository.findById(1L).orElseThrow(()-> new UserException(UserErrorCode.NOT_FOUND));
        Mission mission = missionRepository.findById(req.missionId()).orElseThrow(()-> new MissionException(MissionErrorCode.NOT_FOUND));

        if(userMissionRepository.existsByUserAndMissionAndStatus(user,mission, MissionStatus.IN_PROGRESS)){
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_CHALLENGING);
        }

        UserMission userMission = UserMissionConverter.toUserMission(user,mission);
        userMissionRepository.save(userMission);

        return UserMissionConverter.toJoinMissionResDTO(userMission);
    }
}
