package umc.global.apiPayload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDTO<T> {
    private List<T> content;        // 실제 데이터
    private int page;               // 현재 페이지 번호
    private int size;               // 페이지당 데이터 수
    private long totalElements;     // 전체 데이터 개수
    private int totalPages;         // 총 페이지 수
    private boolean hasNext;        // 다음 페이지 여부
}
