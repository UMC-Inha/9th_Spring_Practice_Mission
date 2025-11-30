package umc.domain.member.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.code.MemberErrorCode;
import umc.domain.member.converter.MemberMissionConverter;
import umc.domain.member.dto.res.MemberMissionResDTO;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.enums.State;
import umc.domain.member.exception.MemberException;
import umc.domain.member.repository.MemberMissionRepository;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.code.MissionErrorCode;
import umc.domain.mission.exception.MissionException;
import umc.global.apiPayload.dto.PageResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public PageResponseDTO<MemberMissionResDTO.goingResDTO> getRunningMissions(Long memberId, Integer page) {

        // 1. memberId 존재 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 2. 페이지 설정
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 3. 진행중인 미션 조회
        Page<MemberMission> result = memberMissionRepository
                .findAllByMemberIdAndState(memberId, State.RUNNING, pageRequest);

        if (result.isEmpty()) {
            throw new MissionException(MissionErrorCode.MISSION_NOT_FOUND);
        }

        // 4. DTO 변환
        List<MemberMissionResDTO.goingResDTO> list = result
                .map(MemberMissionConverter::toGoingResDTO)
                .getContent();

        // 5. PageResponseDTO 생성
        return new PageResponseDTO<>(
                list,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.hasNext()
        );
    }
}
