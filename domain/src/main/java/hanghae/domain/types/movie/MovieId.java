package hanghae.domain.types.movie;

import java.util.Objects;

public class MovieId {

    private final Long movieId;

    // 나중에 UUID 로 리팩토링 해도 좋을 듯
    public MovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getMovieId() {
        return movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieId movieId1 = (MovieId) o;
        return Objects.equals(movieId, movieId1.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(movieId);
    }
}
