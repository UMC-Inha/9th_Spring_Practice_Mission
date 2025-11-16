package umc.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MissionResponseDTO {
    private Long id;
    private String content;
    private Integer point;
    private Integer period;
    private String storeName;
}
