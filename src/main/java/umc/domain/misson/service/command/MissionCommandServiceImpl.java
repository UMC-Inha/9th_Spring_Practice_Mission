package umc.domain.misson.service.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.exception.member.MemberException;
import umc.domain.member.exception.member.code.MemberErrorCode;
import umc.domain.member.exception.membermission.MemberMissionException;
import umc.domain.member.exception.membermission.code.MemberMissionErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.member.repository.membermission.MemberMissionRepository;
import umc.domain.misson.converter.MissionConverter;
import umc.domain.misson.dto.MissionReqDTO;
import umc.domain.misson.dto.MissionResDTO;
import umc.domain.misson.entity.Mission;
import umc.domain.misson.exception.MissionException;
import umc.domain.misson.exception.code.MissionErrorCode;
import umc.domain.misson.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.store.StoreException;
import umc.domain.store.exception.store.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;
import umc.global.util.RandomCodeGenerator;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResDTO.createMissionResDTO createMission(
            Long storeId,
            MissionReqDTO.createMissionReqDTO reqDTO
    ) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));
        Mission mission = MissionConverter.toMission(store, reqDTO, RandomCodeGenerator.generateRandomInt());

        missionRepository.save(mission);
        return MissionConverter.toCreateMissionResDTO(mission);
    }

    @Override
    public MissionResDTO.MissionPreviewDTO updateMissionStatus(
            Long memberId,
            Long missionId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));
        MemberMission memberMission = memberMissionRepository.findMemberMissionByMemberAndMission(member, mission)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.NOT_FOUND));

        memberMission.complete();
        return MissionConverter.toMissionPreviewDTO(mission);
    }
}
