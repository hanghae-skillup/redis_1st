package hellojpa.dto;

import hellojpa.domain.Genre;
import hellojpa.domain.Movie;

import hellojpa.domain.VideoRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningDto {

    private String title;
    private String rating;
    private LocalDate releaseDate;
    private String thumbnail;
    private int runningTime;
    private String genre;
    private List<TheaterScheduleDto> theaterSheduleDtoList = new ArrayList<>();

    public ScreeningDto(String title, VideoRating rating, LocalDate releaseDate, String thumbnail, int runningTime, Genre genre) {
        this.title = title;
        this.rating = rating.toString();
        this.releaseDate = releaseDate;
        this.thumbnail = thumbnail;
        this.runningTime = runningTime;
        this.genre = genre.toString();
    }
}