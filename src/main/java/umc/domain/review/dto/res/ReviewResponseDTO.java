package umc.domain.review.dto.res;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private String content;
    private Integer grade;
    private String storeName;
    private LocalDateTime createdAt;
}
