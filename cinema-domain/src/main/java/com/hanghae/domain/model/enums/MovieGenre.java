package com.hanghae.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieGenre {
    FAMILY("가족", "F"),
    ACTION("액션", "A"),
    THRILLER("스릴러", "T"),
    ROMANCE("로맨스", "R"),
    COMEDY("코메디", "C");

    private final String codeName;  // 사용자에게 보여줄 값
    private final String dbCode; // DB에 저장될 코드값

    //db코드를 enum코드로 변경
    public static MovieGenre fromDbCode(String dbCode) {
        for (MovieGenre genre : values()) {
            if (genre.dbCode.equals(dbCode)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("일치하는 영화 장르 코드 값이 없습니다 : " + dbCode);
    }
}
