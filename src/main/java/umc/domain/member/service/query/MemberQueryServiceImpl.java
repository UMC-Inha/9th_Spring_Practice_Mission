package umc.domain.member.service.query;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MissionChallengeListDto;
import umc.domain.member.dto.MissionListDto;
import umc.domain.member.dto.MyPageDto;
import umc.domain.member.dto.member.MemberReqDTO;
import umc.domain.member.dto.member.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.enums.Status;
import umc.domain.member.exception.member.MemberException;
import umc.domain.member.exception.member.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.member.repository.membermission.MemberMissionRepository;
import umc.global.auth.CustomUserDetails;
import umc.global.auth.JwtUtil;
import umc.global.dto.PageResponse;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MyPageDto getMyPage(Long memberId) {
        return memberRepository.findMyPageById(memberId);
    }

    @Override
    public Long countMissionsByRegionAndStatus(Long memberId, String regionName, Status status) {
        return memberMissionRepository.getMissionCountByMemberIdAndRegion(memberId, regionName, status);
    }

    @Override
    public PageResponse<MissionListDto> getMissions(Long memberId, Pageable pageable) {
        Page<MissionListDto> page = memberMissionRepository.findMissionsByMemberId(memberId, pageable);
        return PageResponse.of(page);
    }

    @Override
    public PageResponse<MissionChallengeListDto> getAvailableMissions(Long memberId, String regionName,
                                                                      Long lastMissionId, Pageable pageable) {

        Page<MissionChallengeListDto> page = memberMissionRepository
                .findAvailableMissionsByMemberIdAndRegion(regionName, memberId, lastMissionId, pageable);
        return PageResponse.of(page);
    }

    @Override
    public MemberResDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto
    ) {

        // Member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }
}

