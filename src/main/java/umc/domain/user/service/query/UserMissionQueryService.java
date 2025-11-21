package umc.domain.user.service.query;

import umc.domain.user.dto.res.UserMissionResDTO;

public interface UserMissionQueryService {
    UserMissionResDTO.MyMissionByStatusListResDTO getMyInProgressMissionList(Integer page);
}
