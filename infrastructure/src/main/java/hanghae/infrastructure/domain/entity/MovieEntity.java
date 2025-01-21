package hanghae.infrastructure.domain.entity;

import hanghae.domain.domain.Movie;
import hanghae.domain.types.movie.*;
import hanghae.infrastructure.domain.converter.ReleaseDateConverter;
import hanghae.infrastructure.domain.converter.RunningTimeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "movies")
@NoArgsConstructor
public class MovieEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Setter
    private String title;

    @Enumerated(EnumType.STRING)
    private AgeRating ageRating;

//    @Convert(converter = ReleaseDateConverter.class)
//    private ReleaseDate releaseDate;

    private LocalDate releaseDate;

    private String thumbnailUrl;

    @Convert(converter = RunningTimeConverter.class)
    private RunningTime runningTime;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OrderBy("schedule ASC")
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShowtimeEntity> showtime = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieTheaterEntity> movieTheater = new ArrayList<>();

    // TODO mapstruct 사용하자 -> 엔티티에서 정적 팩토리 메서드 사용하니까 이름이 직관적이지 않음
    public static Movie toMovieDomain(MovieEntity movieEntity) {
        return Movie.builder()
                .id(new MovieId(movieEntity.getMovieId()))
                .title(movieEntity.getTitle())
                .ageRating(movieEntity.getAgeRating())
//                .releaseDate(movieEntity.getReleaseDate())
                .releaseDate(new ReleaseDate(movieEntity.getReleaseDate()))
                .thumbnailUrl(movieEntity.getThumbnailUrl())
                .runningTime(movieEntity.getRunningTime())
                .genre(movieEntity.getGenre())
                .showtime(ShowtimeEntity.toShowtimeDomainList(movieEntity.getShowtime()))
                .build();
    }

    public void setTitleAndThumbnailUrl(String title, String thumbnailUrl) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setValueObjects(
//            ReleaseDate releaseDate,
            LocalDate releaseDate,
            RunningTime runningTime,
            AgeRating ageRating,
            Genre genre
    ) {
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.ageRating = ageRating;
        this.genre = genre;
    }
}
