package hanghae.application.dto;

import hanghae.domain.domain.Showtime;

import java.util.List;

public record ShowtimeResponse(
   String startTime,
   String endTime,
   List<String> theatersName
) {

    public static ShowtimeResponse from(Showtime showtime) {
        return new ShowtimeResponse(
                showtime.getStartTimeAsString(),
                showtime.getEndTimeAsString(),
                showtime.getTheatersName()
        );
    }
}
