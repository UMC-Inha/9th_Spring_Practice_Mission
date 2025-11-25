package umc.domain.member.service.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.exception.code.MemberMissionErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.entity.MemberMission;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.MissionStatus;
import umc.domain.mission.exception.MissionException;
import umc.domain.mission.exception.code.MissionErrorCode;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    @Override
    public void acceptMission(Long memberId, Long missionId){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND_MEMBER));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND_MISSION));


        boolean alreadyChallenging = memberMissionRepository
                .existsByMemberIdAndMissionIdAndStatus(
                        memberId,
                        missionId,
                        MissionStatus.IN_PROGRESS
                );

        if(alreadyChallenging){
            throw new MissionException(MemberMissionErrorCode.MISSION_NOT_AVAILABLE);
        }

        MemberMission memberMission = MemberMission.challenge(mission, member);
        memberMissionRepository.save(memberMission);
    }

}
