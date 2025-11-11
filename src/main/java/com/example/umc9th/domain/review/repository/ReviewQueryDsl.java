package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.types.Predicate;

import java.util.List;

public interface ReviewQueryDsl {

    // 검색 API , 워크북 내용
    List<Review> searchReview(
            Predicate predicate
    );

    // 미션 , 가게별 별점별
    List<Review> filterReview(
            Long storeId, Float star
    );
}
