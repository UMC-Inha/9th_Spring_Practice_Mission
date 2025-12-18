package umc.domain.mission.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.mission.dto.CreateMissionDto;
import umc.domain.mission.entity.MemberMission;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.enums.Status;
import umc.domain.mission.exception.MissionException;
import umc.domain.mission.exception.code.MissionErrorCode;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionService {


    private MissionRepository missionRepository;
    private MemberRepository memberRepository;
    private MemberMissionRepository memberMissionRepository;
    private StoreRepository storeRepository;



    public Long createMemberMission(Long memberId, Long missionId){

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        MemberMission memberMission
                = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(Status.IN_PROGRESS)
                .build();

        return memberMissionRepository.save(memberMission).getId();



    }


    public Long createMission(Long storeId, CreateMissionDto createMissionDto) {


        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));




        Mission mission
                = Mission.builder()
                .name(createMissionDto.getName())
                .price(createMissionDto.getPrice())
                .point(createMissionDto.getPoint())
                .status(createMissionDto.getStatus())
                .date(createMissionDto.getDate())
                .discription(createMissionDto.getDiscription())
                .store(store)
                .build();

        return missionRepository.save(mission).getId();
    }


    public Page<Mission> findStoreMission(Long storeId,
                                          Pageable pageable){


        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

         return missionRepository.findAllByStore(store, pageable);



    }

    public Page<Mission> findMyInProgressMission(Long memberId,
                                                 Pageable pageable) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return memberMissionRepository.findAllByMemberAndStatus(member, Status.IN_PROGRESS,pageable);

    }



}
