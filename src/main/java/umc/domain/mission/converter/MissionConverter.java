package umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.domain.mission.dto.res.MissionDTO;
import umc.domain.mission.dto.res.MissionResDTO;
import umc.domain.mission.entity.Mission;

public class MissionConverter {
//엔티티 -> MissionDTO
    public static MissionDTO toMissionDTO(Mission mission, String locationName) {
        return MissionDTO.builder()
                .MissionId(mission.getId())
                .locationName(locationName)
                .storeName(mission.getStore().getName())
                .least_amount(mission.getLeastAmount())
                .point(mission.getPoint())
                .deadline(1) // TODO: 데드라인 D-day 계산해서 넣고 싶으면 여기서 처리
                .build();
    }

    //Page<Mission> -> MissionListResult
    public static MissionResDTO.MissionListResult toMissionLIstResult(Page<Mission> missionPage, String locationName) {

        //Page<Mission>  -> Page<MissionDTO>
        Page<MissionDTO> missionDTOPage = missionPage.map(mission -> toMissionDTO(mission, locationName));

        //Page<MissionDTO> -> MissionListResult
        return MissionResDTO.MissionListResult.builder()
                .missions(missionDTOPage.getContent())
                .totalPages(missionDTOPage.getTotalPages())
                .totalElements(missionDTOPage.getTotalElements())
                .currentPage(missionDTOPage.getNumber())
                .size(missionDTOPage.getSize())
                .hasNext(missionDTOPage.hasNext())
                .build();
    }
}