package umc.global.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.member.MemberException;
import umc.domain.member.exception.member.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(member);
    }
}
