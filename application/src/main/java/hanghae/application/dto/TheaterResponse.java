package hanghae.application.dto;

import hanghae.domain.domain.Theater;

// TODO 임시로 극장 이름만 필드로 가짐
public record TheaterResponse(
        String theaterName
) {

    public static TheaterResponse from(Theater theater) {
        return new TheaterResponse(theater.getTheaterName());
    }
}
