package umc.domain.member.service.query;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.domain.member.code.MemberErrorCode;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.req.MemberReqDTO;
import umc.domain.member.dto.res.MemberResponseDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.repository.MemberRepository;
import umc.global.apiPayload.dto.PageResponseDTO;
import umc.global.auth.jwt.JwtUtil;
import umc.global.auth.service.CustomUserDetails;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

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

    public MemberResponseDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto
    ) {

        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID_CREDENTIALS);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }
}

