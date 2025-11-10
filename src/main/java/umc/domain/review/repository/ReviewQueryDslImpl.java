package umc.domain.review.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.domain.review.entity.QReview;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.QStore;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Review> searchReview(Long memberId, String type, String query, Pageable pageable) {

        QReview review = QReview.review;
        QStore store = QStore.store;

        // ✅ 1) 조건 빌더 생성 (Repository 내부에서 책임짐)
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        if (type != null && query != null) {
            if (type.equalsIgnoreCase("store")) {
                builder.and(review.store.name.containsIgnoreCase(query));
            } else if (type.equalsIgnoreCase("grade")) {
                try {
                    int grade = Integer.parseInt(query);
                    builder.and(review.grade.eq(grade));
                } catch (NumberFormatException e) {
                    // 숫자 아닌 경우 무시
                }
            } else if (type.equalsIgnoreCase("both")) {
                String[] queries = query.split("&");
                if (queries.length == 2) {
                    builder.and(review.store.name.containsIgnoreCase(queries[0]));
                    try {
                        int grade = Integer.parseInt(queries[1]);
                        builder.and(review.grade.eq(grade));
                    } catch (NumberFormatException ignored) {}
                }
            }
        }

        // ✅ 2) content 쿼리
        List<Review> results = jpaQueryFactory
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .where(builder)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // ✅ 3) count 쿼리
        Long total = Optional.ofNullable(
                jpaQueryFactory
                        .select(review.count())
                        .from(review)
                        .where(builder)
                        .fetchOne()
        ).orElse(0L);

        return new PageImpl<>(results, pageable, total);
    }
}
