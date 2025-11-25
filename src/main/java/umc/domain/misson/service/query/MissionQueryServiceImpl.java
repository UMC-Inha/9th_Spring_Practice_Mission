package umc.domain.misson.service.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.member.MemberException;
import umc.domain.member.exception.member.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.member.repository.membermission.MemberMissionRepository;
import umc.domain.misson.converter.MissionConverter;
import umc.domain.misson.dto.MissionResDTO;
import umc.domain.misson.entity.Mission;
import umc.domain.misson.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.store.StoreException;
import umc.domain.store.exception.store.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MissionResDTO.MissionPreviewListDTO findMissionsByStore(
            String storeName,
            Integer page
    ) {
        Store store = storeRepository.findByName(storeName)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> results = missionRepository.findAllByStore(store, pageRequest);

        return MissionConverter.toMissionPreviewListDTO(results);
    }

    @Override
    public MissionResDTO.MissionPreviewListDTO findOngoingMissions(
            Long memberId,
            Integer page
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> results = missionRepository.findOngoingMissionsByMember(member, pageRequest);

        return MissionConverter.toMissionPreviewListDTO(results);
    }
}
