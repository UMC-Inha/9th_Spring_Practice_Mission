package umc.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.store.entity.Store;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindMyReviewDto {

    private String store;

    private Integer rate;

    private String discription;

}
