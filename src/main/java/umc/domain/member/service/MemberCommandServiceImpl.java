package umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dtos.MemberReqDTO;
import umc.domain.member.dtos.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.global.auth.enums.Role;



@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    // Password Encoder
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    public MemberResDTO.JoinDTO signup(


            MemberReqDTO.JoinDTO dto
    ) {

        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성: 유저 / 관리자는 따로 API 만들어서 관리
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);


        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .role(member.getRole().name())
                .build();

    }

}



