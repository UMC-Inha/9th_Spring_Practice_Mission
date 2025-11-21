package umc.domain.user.dto.res;

import lombok.Builder;
import umc.domain.user.enums.MissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserMissionResDTO {

    @Builder
    public record JoinMissionResDTO(
            Long userMissionId,
            Long missionId,
            MissionStatus status,
            LocalDateTime createdAt
    ){}

    @Builder
    public record MyMissionByStatusListResDTO(
       List<MyMissionByStatusResDTO> missionList,
       Integer listSize,
       Integer totalPage,
       Long totalElements,
       Boolean isFirst,
       Boolean isLast
    ){}

    @Builder
    public record MyMissionByStatusResDTO(
            Long userMissionId,
            String storeName,
            Integer rewardPoint,
            Integer baseAmount,
            LocalDate deadLine,
            MissionStatus status
    ){}
}
