package com.example.movie.request;

import com.example.movie.dto.GenreDto;
import lombok.Getter;

import static com.example.exception.BusinessError.*;

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
            throw MOVIE_SEARCH_TITLE_ERROR.exception();
        }
        if (this.genre != null && !GenreDto.isValidGenre(this.genre)) {
            throw MOVIE_SEARCH_GENRE_ERROR.exception();
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
