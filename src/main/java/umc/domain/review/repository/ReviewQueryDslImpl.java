package umc.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.domain.review.dto.res.MyReviewResDTO;
import umc.domain.review.repository.query.MyReviewQuery;
import umc.domain.review.service.ReviewSort;

import java.util.List;

import static umc.domain.review.entity.QReview.review;
import static umc.domain.store.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MyReviewResDTO> findMyReviews(Long memberId, MyReviewQuery query, Pageable pageable) {

        BooleanBuilder where = buildWhere(memberId, query);
        OrderSpecifier<?>[] orders = resolveSort(query);

        List<MyReviewResDTO> content = queryFactory
                .select(
                        Projections.constructor(MyReviewResDTO.class,
                        review.id,
                        review.store.id,
                        review.store.name,
                        review.star,
                        review.content,
                        review.createdAt,
                        review.member.id.eq(memberId) // mine
                ))
                .from(review)
                .join(review.store, store)
                .where(where)
                .orderBy(orders)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    private BooleanBuilder buildWhere(Long memberId, MyReviewQuery query) {
        BooleanBuilder where = new BooleanBuilder();

        // 내 리뷰만
        where.and(review.member.id.eq(memberId));

        if (query == null) {
            return where;
        }

        if (query.getStoreId() != null) {
            where.and(review.store.id.eq(query.getStoreId()));
        }

        if (query.getMinStar() != null) {
            where.and(review.star.goe(query.getMinStar()));
        }

        if (query.getMaxStar() != null) {
            where.and(review.star.loe(query.getMaxStar()));
        }

        return where;
    }

    private OrderSpecifier<?>[] resolveSort(MyReviewQuery query) {
        ReviewSort sort = (query == null || query.getSortKey() == null)
                ? ReviewSort.CREATED_DESC
                : query.getSortKey();

        return sort.toOrders();
    }
}
