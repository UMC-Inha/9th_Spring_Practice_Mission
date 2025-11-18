package umc.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.domain.mission.enums.Status;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class CreateMissionDto {

    @Size(max = 10)
    private String name;

    @NotEmpty
    private Integer price;

    @Min(value = 0)
    private Integer point;

    @NotEmpty
    private Status status;

    @NotEmpty
    private LocalDate date;

    @NotEmpty
    private String discription;


}
