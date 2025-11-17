package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.dto.res.MissionDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;



@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService
{

    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.MissionListResult findByLocationName(String locationName, Pageable pageable) {

        // 1. Page<엔티티타입> 조회
        Page<Mission> missionPage = missionRepository.findByName(locationName, pageable);

        // 2. Page<DTO> 타입으로 변환
        Page<MissionDTO> missionDTOPage = missionPage.map(mission ->
                MissionDTO.builder()
                        .MissionId(mission.getId())
                        .locationName(locationName)
                        .storeName(mission.getStore().getName())
                        .least_amount(mission.getLeastAmount())
                        .point(mission.getPoint())
                        .deadline(1) // TODO: 데드라인 D-day 계산해서 넣고 싶으면 여기서 처리
                        .build());

        // 3. MissionResDTO.MissionListResult로 변환
        MissionResDTO.MissionListResult MissionResult = MissionResDTO.MissionListResult.builder()
                .missions(missionDTOPage.getContent())
                .totalPages(missionDTOPage.getTotalPages())
                .totalElements(missionDTOPage.getTotalElements())
                .currentPage(missionDTOPage.getNumber())
                .size(missionDTOPage.getSize())
                .hasNext(missionDTOPage.hasNext())
                .build();


        // 3. Page<DTO> 타입으로 반환
        return MissionResult;
    }
}
