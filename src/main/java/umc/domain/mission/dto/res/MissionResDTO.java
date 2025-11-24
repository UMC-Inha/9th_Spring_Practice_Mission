package umc.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MissionListResult {
        private List<MissionDTO> missions;
        private int totalPages;
        private long totalElements;
        private int currentPage;
        private int size;
        private boolean hasNext;
    }

    @Builder
    public record MissionDTO(
            long MissionId,
            String storeName,
            Integer least_amount,
            Integer point,
            String locationName,
            int deadline
    ) { }

    @Builder
    public record MissionDetailDTO(
            Long missionId,
            Long storeId,
            String storeName,
            LocalDate deadline,
            String conditional,
            Integer point,
            Integer leastAmount
    ) {}

    @Builder
    public record OnGoingMissionDto(
            String storeName,
            Integer point,
            LocalDate deadline,
            Integer leastAmount
    ) { }

    @Builder
    public record CompletedMissionDto(
            Long missionId,
            String storeName,
            Integer point,
            Integer leastAmount
    ) { }

    @Getter
    @Builder
    public static class OnGoingMissionListResult {
        private Long memberId;
        private List<OnGoingMissionDto> missions;
        private int totalCount;
        private int currentPage;
        private int pageSize;
    }

    @Getter
    @Builder
    public static class CompletedMissionListResult {
        private Long memberId;
        private List<CompletedMissionDto> missions;
        private int totalCount;
    }

    @Builder
    public record GetChallengeMissionResDTO(
            Long memberMissionId,
            Long memberId,
            Long missionId,
            Boolean isCompleted
    ){}
}
