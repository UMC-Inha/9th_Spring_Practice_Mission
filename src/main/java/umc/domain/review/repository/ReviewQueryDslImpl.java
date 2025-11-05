package umc.domain.review.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
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
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public ReviewQueryDslImpl(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<ReviewDto> searchReviewByMemberId(Predicate predicate) {

        QReview review = QReview.review;
        QMember member = QMember.member;
        QReviewPhoto photo = QReviewPhoto.reviewPhoto;
        QReviewReply reply = QReviewReply.reviewReply;

        return query.from(review)
                .join(review.member, member)
                .leftJoin(review.reviewPhotoList, photo)
                .leftJoin(review.reviewReplyList, reply)
                .where(predicate)
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
}
