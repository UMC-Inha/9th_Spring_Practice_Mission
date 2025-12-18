package umc.domain.member.service.query;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.GetMemberResponse;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.dto.MemberResDTO.LoginDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.global.security.CustomUserDetails;
import umc.global.security.JwtUtil;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public GetMemberResponse getMember(Long memberId) {
        return memberRepository.findMemberInfo(memberId);
    }

    @Override
    public MemberResDTO.LoginDTO login(MemberReqDTO.@Valid LoginDTO dto) {

        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));

        if (!passwordEncoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        CustomUserDetails userDetails = new CustomUserDetails(member);

        String accessToken = jwtUtil.createAccessToken(userDetails);

        return MemberConverter.toLoginDTO(member, accessToken);
    }


}
