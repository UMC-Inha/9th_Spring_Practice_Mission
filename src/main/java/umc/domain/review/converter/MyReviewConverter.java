package umc.domain.review.converter;

import org.springframework.data.domain.Page;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.repository.query.MyReviewQuery;
import umc.domain.review.service.ReviewSort;

import static umc.domain.review.dto.req.ReviewReqDTO.*;

public class MyReviewConverter {

    //요청 DTO -> 쿼리 객체
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

    // 🔹 Page<MyReviewResponse> -> MyReviewListResponse
    public static ReviewResDTO.MyReviewListResponse toMyReviewListResponse(
            Page<ReviewResDTO.MyReviewResponse> page
    ) {
        return new ReviewResDTO.MyReviewListResponse(
                page.getContent(),          // myReviewList
                page.getNumber(),           // page
                page.getSize(),             // size
                page.getTotalPages(),       // totalPages
                page.getTotalElements(),    // totalElements
                page.isFirst(),             // isFirst
                page.isLast(),              // isLast
                page.hasNext()              // hasNext
        );
    }
}
