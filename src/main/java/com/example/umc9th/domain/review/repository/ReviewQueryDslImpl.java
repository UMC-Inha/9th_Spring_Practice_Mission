package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.member.entity.QMember;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QRegion;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final EntityManager em;

    @Override
    public List<Review> searchReview (
            Predicate predicate
    ){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QReview review = QReview.review;
        QRegion region = QRegion.region;
        QStore store = QStore.store;

        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(region).on(region.id.eq(store.region.id))
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Review> filterReview(Long storeId, Float star) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;
        QStore store = QStore.store;
        QRegion region = QRegion.region;

        // 동적 predicate 생성
        BooleanBuilder builder = new BooleanBuilder();

        if(storeId != null) {
            builder.and(review.store.id.eq(storeId));
        }

        if(star != null) {
            builder.and(review.star.goe(star));
        }

        return queryFactory
                .selectFrom(review)
                .leftJoin(review.store, store)
                // .leftJoin(store.region, region)
                .where(builder)
                .fetch();
    }
}
