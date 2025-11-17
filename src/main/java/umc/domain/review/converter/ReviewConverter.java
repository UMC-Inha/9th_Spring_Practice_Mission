package umc.domain.review.converter;

import umc.domain.review.dto.req.MyReviewReqDTO;
import umc.domain.review.repository.query.MyReviewQuery;
import umc.domain.review.service.ReviewSort;

public class ReviewConverter {

    private ReviewConverter() {
    }

    public static MyReviewQuery toMyReviewQuery(MyReviewReqDTO reqDTO) {

        if (reqDTO == null) {
            return MyReviewQuery.builder()
                    .sortKey(ReviewSort.CREATED_DESC)
                    .build();
        }

        return MyReviewQuery.builder()
                .storeId(reqDTO.getStoreId())
                .minStar(reqDTO.getMinStar())
                .maxStar(reqDTO.getMaxStar())
                .sortKey(
                        reqDTO.getSortKey() != null
                                ? reqDTO.getSortKey()
                                : ReviewSort.CREATED_DESC
                )
                .build();
    }
}
