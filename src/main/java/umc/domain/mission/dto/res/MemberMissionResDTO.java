package umc.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

// 추가 정보를 주기 위한 Wrapper DTO
//ex) totalCount, memberID, ...등등 이 안의 내용은 자유롭게 넘기기 가능.
//단순히 memberMission만 넘기는 건      private List<OnGoingMissionDto> missions;  이거임.

public class MemberMissionResDTO {
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
}
