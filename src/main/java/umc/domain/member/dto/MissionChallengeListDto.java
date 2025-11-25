package umc.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionChallengeListDto {

    public String storeName;
    public String storeType;
    public String description;
    public Integer points;
    public Integer endDays;
    public Long missionId;
}
