package com.example.application.moviescreening;

import com.example.domain.movies.service.MovieService;
import com.example.domain.screening.Screening;
import com.example.domain.screening.service.ScreeningService;
import com.example.domain.theater.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MovieScreeningFacade {

    private final ScreeningService screeningService;
    private final MovieService movieService;
    private final TheaterService theaterService;

    @Transactional
    public void enrollScreen(Long movieId,Long theaterId ,Screening screening){
        var movie = movieService.getMovieInfo(movieId);
        var theater = theaterService.getTheaterInfo(theaterId);

        if(movie == null) throw new NoSuchElementException("No Movie");
        if (theater == null) throw new NoSuchElementException("No Theater");
        //entity move
        if(!screening.checkReleaseDate(movie.getReleaseDate())){
            throw new NoSuchElementException("상영시간은 개봉일 전일 수 없습니다.");
        }

        screening.setMovie(movie);
        screening.setTheater(theater);

        screeningService.enrollScreening(screening);
    }

}
