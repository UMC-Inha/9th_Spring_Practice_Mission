package umc.domain.mission.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import umc.domain.mission.enums.Status;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class MissionDto {

    private Long id;

    private String name;

    private Integer price;

    private Integer point;

    private Status status;

    private LocalDate date;

    private String discription;

}
