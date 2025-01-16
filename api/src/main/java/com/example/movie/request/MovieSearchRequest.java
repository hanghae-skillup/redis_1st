package com.example.movie.request;

import com.example.movie.dto.GenreDto;
import lombok.Getter;

@Getter
public class MovieSearchRequest {
    private String title;
    private String genre;

    public MovieSearchRequest(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    private void validate() {
        if (this.title != null && this.title.length() > 225) {
            throw new IllegalArgumentException("영화 제목은 225자 이하로 입력해주세요");
        }
        if (this.genre != null && !GenreDto.isValidGenre(this.genre)) {
            throw new IllegalArgumentException("유효하지않은 장르입니다");
        }
    }

    public MovieSearchServiceRequest toServiceRequest() {
        this.validate();
        return MovieSearchServiceRequest.builder()
                .title(this.title)
                .genre(this.genre)
                .build();
    }
}
