package umc.domain.review.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.QMember;
import umc.domain.review.dto.QReviewDto;
import umc.domain.review.dto.QReviewPhotoDto;
import umc.domain.review.dto.QReviewReplyDto;
import umc.domain.review.dto.ReviewDto;
import umc.domain.review.entity.QReview;
import umc.domain.review.entity.QReviewPhoto;
import umc.domain.review.entity.QReviewReply;

@Service
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final ReviewRepository reviewRepository;
    private final EntityManager em;

    @Override
    public List<ReviewDto> searchReviewByMemberId(Predicate predicate) {

        JPAQueryFactory query = new JPAQueryFactory(em);

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
