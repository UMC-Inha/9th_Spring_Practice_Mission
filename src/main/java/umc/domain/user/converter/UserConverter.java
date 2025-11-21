package umc.domain.user.converter;

import umc.domain.user.dto.req.UserReqDTO;
import umc.domain.user.dto.res.UserResDTO;
import umc.domain.user.entity.User;

public class UserConverter {

    //Entity -> DTO
    public static UserResDTO.JoinDTO toJoinDTO(User user) {
        return UserResDTO.JoinDTO.builder()
                .userId(user.getId())
                .createAt(user.getCreatedAt())
                .build();
    }

    //DTO -> Entity
    public static User toUser(UserReqDTO.JoinDto joinDto){
        return User.builder()
                .name(joinDto.name())
                .birth(joinDto.birth())
                .address(joinDto.address())
                .detailAddress(joinDto.specAddress())
                .gender(joinDto.gender())
                .build();
    }
}
