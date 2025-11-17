package umc.domain.member.converter;

import umc.domain.member.dto.res.MemberResDTO;
import umc.domain.member.entity.Member;

public class MypageConverter {
    public static MemberResDTO.Mypage toMypage(Member member) {
        return MemberResDTO.Mypage.builder()
                .id(member.getId())
                .name(member.getName())
                .gender(member.getGender())
                .build();
    }

}
