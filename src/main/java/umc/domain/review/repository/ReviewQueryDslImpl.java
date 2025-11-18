package umc.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.domain.region.entity.QRegion;
import umc.domain.review.entity.QReview;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.QStore;
import umc.domain.user.entity.QUser;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    private final JPAQueryFactory queryFactory;

    @Override //워크북때 했던 코드
    public List<Review> searchReview(Predicate predicate){
        //Q클래스 선언
        QReview review = QReview.review;
        QStore store = QStore.store;
        QRegion region = QRegion.region;
        return queryFactory
                .selectFrom(review)
                .leftJoin(store).on(store.id.eq(review.store.id))
                .leftJoin(region).on(region.id.eq(review.store.region.id))
                .where(predicate)
                .fetch();
    };

    //과제(미션 코드)
    @Override
    public List<Review> findMyReviewsFiltered(Long userId, Long storeId, Integer starFloor){

        QReview review = QReview.review;
        QStore store = QStore.store;
        QUser user = QUser.user;

        return queryFactory
                .selectFrom(review) //내가 쓴 리뷰이므로 user,store가 없는 경우는 없다고 가정(INNER JOIN)
                .join(review.user, user)
                .join(review.store, store).fetchJoin()
                .where(
                        //내가 쓴 리뷰
                        user.id.eq(userId),

                        //가게 Id
                        storeIdEq(storeId),

                        //별점
                        ratingEq(starFloor)
                )
                .orderBy(review.createdAt.desc())
                .fetch();
    }

    //동적 쿼리를 위한 private 메서드

    private BooleanExpression storeIdEq(Long storeId) {
        // storeId가 null이면 where절에 추가되지 않음 (null 반환)
        return storeId != null ? QStore.store.id.eq(storeId) : null;
    }

    private BooleanExpression ratingEq(Integer starFloor) {
        // starFloor가 null이면 where절에 추가되지 않음 (null 반환)
        return starFloor != null ? QReview.review.rating.eq(starFloor) : null;
    }
}
