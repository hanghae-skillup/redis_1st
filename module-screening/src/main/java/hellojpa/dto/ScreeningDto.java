package hellojpa.dto;

import hellojpa.domain.Movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningDto {

    private String title;
    private String videoRating;
    private LocalDate releaseDate;
    private String thumbnail;
    private int runningTime;
    private String genreName;
    private List<TheaterScheduleDto> theaterSheduleDtoList;

    public static ScreeningDto of(Movie movie, List<TheaterScheduleDto> theaterSchedule){
        String title = movie.getTitle();
        String videoRating = movie.getRating().toString();
        LocalDate releaseDate = movie.getReleaseDate();
        String thumbnail = movie.getThumbnail();
        int runningTime = movie.getRunningTime();
        String genreName = movie.getGenre().toString();

        return new ScreeningDto(
                title, videoRating, releaseDate, thumbnail,
                runningTime, genreName, theaterSchedule
        );
    }
}
