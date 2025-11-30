package umc.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.code.MemberMissionErrorCode;
import umc.domain.member.converter.MemberMissionConverter;
import umc.domain.member.dto.req.MemberMissionReqDTO;
import umc.domain.member.dto.res.MemberMissionResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.exception.MemberMissionException;
import umc.domain.member.repository.MemberMissionRepository;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.member.enums.State;



@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public MemberMissionResDTO.createResDTO addMission(
            MemberMissionReqDTO.createReqDTO dto
    ){
        // Member 조회
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MEMBER_NOT_FOUND));

        // Mission 조회
        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MISSION_NOT_FOUND));

        // 중복 수행 여부 확인
        boolean alreadyExists = memberMissionRepository.findByMemberIdAndMissionId(member.getId(), mission.getId()).isPresent();
        if (alreadyExists) {
            throw new MemberMissionException(MemberMissionErrorCode.MEMBER_MISSION_ALREADY_EXISTS);
        }

        // Entity 생성
        MemberMission memberMission = MemberMissionConverter.toMemberMission(dto, member, mission);


        memberMissionRepository.save(memberMission);

        // DTO 변환 후 반환
        return MemberMissionConverter.toResDTO(memberMission);
    }

    @Override
    @Transactional
    public MemberMissionResDTO.completeResDTO completeMission(Long memberMissionId) {

        MemberMission mm = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.MISSION_NOT_FOUND));

        if (mm.getState() == State.SUCCESS) {
            throw new IllegalStateException("이미 완료된 미션입니다.");
        }

        // 상태 변경 (서비스에서 직접 변경)
        mm.setState(State.SUCCESS);

        // ResDTO 변환까지 Command Service에서 수행
        return MemberMissionConverter.toCompleteResDTO(mm);
    }

}
