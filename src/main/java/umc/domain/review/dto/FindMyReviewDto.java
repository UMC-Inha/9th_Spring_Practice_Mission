package umc.domain.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.store.entity.Store;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindMyReviewDto {

    private String store;

    private Integer rate;

    private String discription;

}
