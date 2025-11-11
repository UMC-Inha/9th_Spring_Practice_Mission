package umc.domain.review.dto;

import java.time.LocalDateTime;

public record ReviewCard(
        Long reviewId,
        Long storeId,
        String storeName,
        Float star,
        String content,
        LocalDateTime createdAt,
        boolean mine
) {}
