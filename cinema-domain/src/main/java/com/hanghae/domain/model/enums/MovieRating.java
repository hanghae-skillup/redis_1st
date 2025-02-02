package com.hanghae.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieRating {
    ALL("전체이용가", "1"),
    TWELVE("12세", "2"),
    FIFTEEN("15세", "3"),
    ADULT("청소년 관람불가", "4");

    private final String codeName;  // 사용자에게 보여줄 값
    private final String dbCode; // DB에 저장될 코드값

    //db코드를 enum코드로 변경
    public static MovieRating fromDbCode(String dbCode) {
        for (MovieRating rating : values()) {
            if (rating.dbCode.equals(dbCode)) {
                return rating;
            }
        }
        throw new IllegalArgumentException("일치하는 영상물 등급 코드 값이 없습니다 : " + dbCode);
    }
}
