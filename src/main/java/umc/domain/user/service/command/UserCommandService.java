package umc.domain.user.service.command;


import umc.domain.user.dto.req.UserReqDTO;
import umc.domain.user.dto.res.UserResDTO;

public interface UserCommandService {

    //회원가입 로직
    UserResDTO.JoinDTO signup(UserReqDTO.JoinDto joinDto);
}
