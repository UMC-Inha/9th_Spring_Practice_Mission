package umc.domain.food.enums;

public enum FoodName {
    WESTERN("양식"),
    CHINESE("중식"),
    KOREAN("한식");

    private final String koreanName;

    FoodName(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }
}
    