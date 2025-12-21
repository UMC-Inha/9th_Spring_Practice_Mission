package umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import umc.domain.member.dtos.MemberReqDTO;
import umc.domain.member.dtos.MemberResDTO;
import umc.global.config.CustomUserDetails;


@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{

    private final AuthenticationManager authenticationManager;

    @Override
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto) {


        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );

        CustomUserDetails user =
                (CustomUserDetails) authentication.getPrincipal();

        return MemberResDTO.LoginDTO.builder()
                .memberId(user.getMember().getId())
                .accessToken(null) // 세션 기반이라 토큰 없음
                .build();
    }
}
