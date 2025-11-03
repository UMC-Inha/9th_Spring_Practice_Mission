package umc.domain.member.dto;

public class MissionChallengeListDto {
    public String storeName;
    public String storeType;
    public String description;
    public int points;
    public int endDays;
    public Long missionId;

    public MissionChallengeListDto(String storeName, String storeType, String description
            , int points, int endDays, Long missionId) {
        this.storeName = storeName;
        this.storeType = storeType;
        this.description = description;
        this.points = points;
        this.endDays = endDays;
        this.missionId = missionId;
    }
}
