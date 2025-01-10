package com.example.movie;

import com.example.movie.convertor.DtoConvertor;
import com.example.movie.dto.MoviesNowShowingDbDto;
import com.example.movie.response.MoviesNowShowingDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private DtoConvertor dtoConvertor = new DtoConvertor();

    public List<MoviesNowShowingDetail> getNowShowingMovies() {
        List<MoviesNowShowingDbDto> dbResults = movieRepository.findNowShowing(LocalDate.now());
        List<MoviesNowShowingDetail> detailsList = dtoConvertor.moviesNowScreening(dbResults);

        return detailsList.stream()
                .sorted(Comparator.comparing(MoviesNowShowingDetail::releaseDate))
                .toList();
    }

}
