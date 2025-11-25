package umc.domain.member.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.converter.MemberMissionConverter;
import umc.domain.member.dto.membermission.MemberMissionResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.exception.member.MemberException;
import umc.domain.member.exception.member.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.member.repository.membermission.MemberMissionRepository;
import umc.domain.misson.entity.Mission;
import umc.domain.misson.exception.MissionException;
import umc.domain.misson.exception.code.MissionErrorCode;
import umc.domain.misson.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMissionResDTO.challengeMissionResDTO challengeMission(
            Long missionId,
            Long memberId
    ) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        MemberMission memberMission = MemberMissionConverter.toMemberMission(mission, member);

        memberMissionRepository.save(memberMission);
        mission.getMemberMissionList().add(memberMission);
        member.getMemberMissionList().add(memberMission);

        return MemberMissionConverter.toChallengeMissionResDTO(memberMission);
    }
}
