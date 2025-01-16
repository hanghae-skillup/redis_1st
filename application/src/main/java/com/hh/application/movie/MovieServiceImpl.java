package com.hh.application.movie;

import com.hh.domain.movie.dto.MovieDto;
import com.hh.domain.movie.dto.MovieScreeningDto;
import com.hh.domain.movie.dto.ScreenDto;
import com.hh.domain.movie.Screen;
import com.hh.infrastructure.movie.MovieRepository;
import com.hh.infrastructure.movie.MovieScreeningRepository;
import com.hh.infrastructure.movie.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;
  private final ScreenRepository screenRepository;
  private final MovieScreeningRepository movieScreeningRepository;

  public List<ScreenDto> getMovieScreen() {

    List<Screen> MovieScreenList = screenRepository.findAll();
    return MovieScreenList.stream().map(ScreenDto::from).toList();
  }

  @Override
  public List<MovieDto> getMovies() {
    return movieRepository.findAll().stream().map(MovieDto::from).toList();
  }

  @Override
  public List<MovieScreeningDto> findMoviesWithGroupConcat() {
    // Pageable 객체 생성 (페이지 번호: 0, 페이지 크기: 10)
    Pageable pageable = PageRequest.of(0, 10);

    // 검색 키워드
    String searchKeyword = "";

    // 오늘 날짜 및 시간
    LocalDateTime today = LocalDateTime.now();

    // Service 메서드 호출
    List<MovieScreeningDto> result = movieScreeningRepository.findMoviesWithGroupConcat(pageable, searchKeyword, today);

    // 결과 출력
    for (MovieScreeningDto dto : result) {
      System.out.println(dto.toString());
    }
    return result;
  }
}
