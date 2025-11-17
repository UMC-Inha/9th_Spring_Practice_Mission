package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.converter.MemberMissionConverter;
import umc.domain.mission.dto.res.CompletedMissionDto;
import umc.domain.mission.dto.res.OnGoingMissionDto;
import umc.domain.mission.exception.MissionErrorCode;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.global.apiPayload.Exception.GeneralException;

import java.util.List;

import static umc.domain.mission.dto.res.MemberMissionResDTO.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public OnGoingMissionListResult getOnGoingMissions(Long memberId, Pageable pageable) {

        // 1. 회원 검증
        validateMember(memberId);

        // 2. 레포지토리에서 진행중 미션 조회
        List<OnGoingMissionDto> missions =
                memberMissionRepository.findOnGoingMissions(memberId, pageable);

        // 3. 미션 없으면 예외
        if (missions.isEmpty()) {
            throw new GeneralException(MissionErrorCode.ONGOING_MISSION_NOT_FOUND);
        }

        // 4. Converter로 응답 DTO 만들어서 반환
        return MemberMissionConverter.toOnGoingMissionListResult(memberId, missions);
    }

    @Override
    public CompletedMissionListResult getCompletedMissions(Long memberId, Pageable pageable) {

        // 1. 회원 검증
        validateMember(memberId);

        // 2. 레포지토리에서 완료 미션 조회
        List<CompletedMissionDto> missions =
                memberMissionRepository.findCompletedMissions(memberId, pageable);

        // 3. 미션 없으면 예외
        if (missions.isEmpty()) {
            throw new GeneralException(MissionErrorCode.COMPLETED_MISSION_NOT_FOUND);
        }

        // 4. Converter로 응답 DTO
        return MemberMissionConverter.toCompletedMissionListResult(memberId, missions);
    }

    private void validateMember(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new GeneralException(MissionErrorCode.MEMBER_NOT_FOUND);
        }
    }
}
