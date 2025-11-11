package umc.domain.review.dto;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.dsl.*;

public class QReviewCard extends ConstructorExpression<ReviewCard> {
    public QReviewCard(
            NumberExpression<Long> reviewId,
            NumberExpression<Long> storeId,
            StringExpression storeName,
            NumberExpression<Float> star,
            StringExpression content,
            DateTimeExpression<java.time.LocalDateTime> createdAt,
            BooleanExpression mine
    ) {
        super(ReviewCard.class, new Class<?>[]{
                Long.class, Long.class, String.class, Float.class, String.class,
                java.time.LocalDateTime.class, boolean.class
        }, reviewId, storeId, storeName, star, content, createdAt, mine);
    }
}
