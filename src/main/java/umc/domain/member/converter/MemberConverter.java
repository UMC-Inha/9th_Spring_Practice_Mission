package umc.domain.member.converter;

import umc.domain.member.dto.res.MemberResponseDTO;
import umc.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResponseDTO.SearchDTO toSearchDTO(Member member) {
        return new MemberResponseDTO.SearchDTO(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getAddress(),
                member.getPoint()
        );
    }
}
