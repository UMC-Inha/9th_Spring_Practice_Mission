package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.converter.GetChallengeMissionConverter;
import umc.domain.mission.dto.res.GetChallengeMissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.exception.MissionErrorCode;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.mission.repository.MissionRepository;
import umc.global.apiPayload.Exception.GeneralException;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public GetChallengeMissionResDTO getChallengeMission(Long memberId, Long missionId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()->new GeneralException(MemberErrorCode.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(()->new GeneralException(MissionErrorCode.MISSION_NOT_FOUND));
        MemberMission saved = memberMissionRepository.save(GetChallengeMissionConverter.toMemberMission(member, mission));

        return GetChallengeMissionConverter.toGetChallengeMissionResDTO(saved);
    }
}
