package com.example.movie;

import com.example.movie.response.MoviesNowShowingDetail;
import com.example.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/list/now-showing")
    public BaseResponse<List<MoviesNowShowingDetail>> getCategories() {
        List<MoviesNowShowingDetail> response = movieService.getNowShowingMovies();
        return new BaseResponse<>(response);
    }

}
