package org.example.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Genre {
    ACTION("액션"),
    DRAMA("드라마"),
    COMEDY("코미디"),
    HORROR("공포"),
    THRILLER("스릴러"),
    ROMANCE("로맨스"),
    SCI_FI("SF"),
    DOCUMENTARY("다큐멘터리"),
    FANTASY("판타지"),
    ANIMATION("애니메이션");

    private final String displayName;
}
