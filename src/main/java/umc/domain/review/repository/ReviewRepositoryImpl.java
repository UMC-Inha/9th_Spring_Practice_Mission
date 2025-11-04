package umc.domain.review.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.domain.review.entity.Review;

import java.util.List;

import static umc.domain.review.entity.QReview.review;
import static umc.domain.store.entity.QStore.store;
import static umc.domain.user.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {


    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> findMyReviews(Long memberId,
                                      Long storeId, Integer rateFilter,
                                      Boolean sortByStore, Boolean sortByRate,Pageable pageable
                                      ) {


        BooleanExpression condition = review.member.id.eq(memberId);

        if (storeId != null) {
            condition = condition.and(review.store.id.eq(storeId));
        }

        if (rateFilter != null) {
            condition = condition.and(review.rate.eq(rateFilter));
        }


        List<Review> results = jpaQueryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .join(review.member, member).fetchJoin()
                .where(condition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(
                        sortByStore != null && sortByStore ? store.name.asc() : null,
                        sortByRate != null && sortByRate ? review.rate.desc() : null
                )
                .fetch();


        Long totalCount = jpaQueryFactory
                .select(review.count())
                .from(review)
                .where(condition)
                .fetchOne();


        return new PageImpl<>(results, pageable, totalCount != null  ? totalCount : 0);


    }
}
