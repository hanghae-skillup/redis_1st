package project.redis.domain.movie;

import lombok.Getter;

@Getter
public enum RatingClassification {
    ALL("전체관람가"),
    TWELVE("12세 이상 관람가"),
    FIFTEEN("15세 이상 관람가"),
    NINETEEN("19세 이상 관림가"),
    RESTRICT("제한상영가");

    private final String description;

    RatingClassification(String description) {
        this.description = description;
    }
}
