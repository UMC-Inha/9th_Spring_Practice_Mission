package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.converter.MissionConverter;
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

        //  Page<엔티티타입> 조회
        Page<Mission> missionPage = missionRepository.findByName(locationName, pageable);



        // Converter에게  변환 위임
        return MissionConverter.toMissionLIstResult(missionPage,locationName);
    }
}
