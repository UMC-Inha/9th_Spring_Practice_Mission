package umc.domain.review.service;

public enum ReviewSort {
    CREATED_DESC,   // 최신순
    STAR_DESC,      // 별점 높은순
    STAR_ASC;       // 별점 낮은순

    public static ReviewSort from(String raw) {
        if (raw == null || raw.isBlank()) return CREATED_DESC;
        try { return ReviewSort.valueOf(raw.toUpperCase()); }
        catch (IllegalArgumentException e) { return CREATED_DESC; }
    }
}