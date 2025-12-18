package umc.domain.mission.converter;

import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;

public class MissionConverter {

    public static MissionResDTO.SearchListDTO toSearchListDTO(
            Page<Mission> result
    ) {
        return MissionResDTO.SearchListDTO.builder()
                .missionList(
                        result.getContent().stream()
                                .map(MissionConverter::toSearchDTO)
                                .collect(Collectors.toList())
                )
                .listSize(result.getNumberOfElements())
                .totalPage(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .isFirst(result.isFirst())
                .isLast(result.isLast())
                .build();
    }

    public static MissionResDTO.SearchDTO toSearchDTO(
            Mission result
    ) {
        return MissionResDTO.SearchDTO.builder()
                .missionId(result.getId())
                .content(result.getContent())
                .point(result.getPoint())
                .deadlineAt(result.getDeadlineAt())
                .build();
    }

}
