package umc.domain.user.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.user.converter.UserMissionConverter;
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
@Transactional(readOnly = true)
public class UserMissionQueryServiceImpl implements UserMissionQueryService{
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    @Override
    public UserMissionResDTO.MyMissionByStatusListResDTO getMyInProgressMissionList(Integer page){
        //유저 조회(하드코딩)
        User user = userRepository.findById(1L).orElseThrow(()-> new UserException(UserErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page-1,10);

        Page<UserMission> missionPage = userMissionRepository.findUserMissionsByStatus(
                user.getId(),
                MissionStatus.IN_PROGRESS,
                pageRequest
        );

        return UserMissionConverter.toMyMissionByStatusListResDTO(missionPage);
    }
}
