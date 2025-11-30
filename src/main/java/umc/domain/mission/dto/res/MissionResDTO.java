package umc.domain.mission.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MissionResDTO {
    @Builder
    public record CreateResDTO(
            Long missionId
    ){}

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionResponseDTO {
        private Long id;
        private String content;
        private Integer point;
        private Integer period;
        private String storeName;
    }

    @Builder
    public record StoreMissionDTO(
            String storeName,
            String content,
            Integer point,
            Integer period
    ){}
}
