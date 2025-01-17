package hellojpa.domain;

public enum VideoRating {

    ALL("전체관람가"),
    AGE_12("12세이상관람가"),
    AGE_15("15세이상관람가"),
    AGE_19("청소년관람불가"),
    RESTRICTED("제한관람가");

    private final String description;

    VideoRating(String description) {
        this.description = description;
    }
}
