package umc.domain.member.converter;

import umc.domain.Region.entity.Region;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.global.auth.enums.Role;

public class MemberConverter {

    // member -> MemberResDTO.MyPageMemberDTO
    public static MemberResDTO.MyPageMemberDTO toMyPageMemberDTO(Member member) {
        return MemberResDTO.MyPageMemberDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    // Member -> JoinDTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // JoinDTO -> Member
    public static Member toMember(
            Region region,
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ){

        return Member.builder()
                .name(dto.name())
                .email(dto.email())
                .birth(dto.birth())
                .region(region)
                .role(role)
                .password(password)
                .detailAddress(dto.detailAddress())
                .gender(dto.gender())
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(Member member, String accessToken) {
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}
