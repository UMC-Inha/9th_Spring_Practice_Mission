package umc.domain.review.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.QMember;
import umc.domain.review.dto.QReviewDto;
import umc.domain.review.dto.QReviewPhotoDto;
import umc.domain.review.dto.QReviewReplyDto;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.entity.QReview;
import umc.domain.review.entity.QReviewPhoto;
import umc.domain.review.entity.QReviewReply;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewDto> searchReviewByMemberId(Long memberId, String storeName, BigDecimal starRating) {

        QReview review = QReview.review;
        QMember member = QMember.member;
        QReviewPhoto photo = QReviewPhoto.reviewPhoto;
        QReviewReply reply = QReviewReply.reviewReply;

        return queryFactory.from(review)
                .join(review.member, member)
                .leftJoin(review.reviewPhotoList, photo)
                .leftJoin(review.reviewReplyList, reply)
                .where(
                        memberIdEq(memberId, review),
                        storeNameEq(storeName, review),
                        starRatingEq(starRating, review)
                )
                .transform(GroupBy.groupBy(review.id).list(
                                new QReviewDto(
                                        review.id,
                                        member.name,
                                        review.starRating,
                                        GroupBy.list(new QReviewPhotoDto(photo.photoUrl)),
                                        GroupBy.list(new QReviewReplyDto(reply.content))
                                )
                        )
                );
    }

    private Predicate memberIdEq(Long memberId, QReview review) {
        return (memberId == null) ? null : review.member.id.eq(memberId);
    }

    private Predicate storeNameEq(String storeName, QReview review) {
        return (storeName == null) ? null : review.store.name.eq(storeName);
    }

    private Predicate starRatingEq(BigDecimal starRating, QReview review) {
        return (starRating == null) ? null : review.starRating.eq(starRating);
    }
}
