package umc.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.domain.review.dto.MyReviewQuery;
import umc.domain.review.dto.QReviewCard;
import umc.domain.review.dto.ReviewCard;
import umc.domain.review.service.ReviewSort;

import java.util.List;

import static umc.domain.review.entity.QReview.review;
import static umc.domain.store.entity.QStore.store;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewCard> findMyReviews(Long memberId, MyReviewQuery req, Pageable pageable) {

        BooleanBuilder where = new BooleanBuilder();
        where.and(review.member.id.eq(memberId));


        OrderSpecifier<?>[] orders = toOrderSpecifiers(req.sortKey());

        List<ReviewCard> content = queryFactory
                .select(new QReviewCard(
                        review.id,
                        review.store.id,
                        review.store.name,
                        review.star,
                        review.content,
                        review.createdAt,
                        review.member.id.eq(memberId)
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
                .where(where)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0 : total);
    }

    private OrderSpecifier<?>[] toOrderSpecifiers(ReviewSort sort) {
        return switch (sort) {
            case STAR_DESC -> new OrderSpecifier[]{review.star.desc(), review.id.desc()};
            case STAR_ASC  -> new OrderSpecifier[]{review.star.asc(), review.id.desc()};
            case CREATED_DESC -> new OrderSpecifier[]{review.createdAt.desc(), review.id.desc()};
        };
    }
}
