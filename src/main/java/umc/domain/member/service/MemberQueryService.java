package umc.domain.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.res.MemberResponseDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;
import umc.global.apiPayload.dto.PageResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public PageResponseDTO<MemberResponseDTO.SearchDTO> getMembers(Pageable pageable) {

        Page<Member> members = memberRepository.findAll(pageable);

        List<MemberResponseDTO.SearchDTO> list = members
                .map(MemberConverter::toSearchDTO)
                .getContent();

        return new PageResponseDTO<>(
                list,
                members.getNumber(),
                members.getSize(),
                members.getTotalElements(),
                members.getTotalPages(),
                members.hasNext()
        );
    }
}
