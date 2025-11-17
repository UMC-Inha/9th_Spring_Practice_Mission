package umc.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    public static class MissionListResult {
        private List<MissionDTO> missions; // 페이징을 사용하더라도 여기서는 list로 받음. repository에서 page를 쓰더라도 list로 받고 넘겨줌
        private int totalPages; // 전체 페이지 수
        private long totalElements; // 전체 요소 수
        private int currentPage; // 현재 페이지 번호
        private int size; // 현재 페이지의 요소 개수
        private boolean hasNext; // 다음 페이지 존재 여부
    }
}
