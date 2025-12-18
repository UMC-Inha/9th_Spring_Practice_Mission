package umc.domain.member.converter;

import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.MemberStatus;
import umc.domain.member.entity.MemberType;
import umc.global.security.CustomUserDetails;

public class MemberConverter {

    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(
            Member member,
            String accessToken
    ){
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }

    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            MemberType memberType
    ){
        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .point(0)
                .memberType(memberType)
                .phoneNumber(dto.phoneNumber())
                .memberStatus(MemberStatus.ACTIVE)
                .build();

    }

}
