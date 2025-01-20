package com.hh.application.movie;

import com.hh.domain.movie.Genre;
import com.hh.domain.movie.dto.MovieScreeningDto;
import com.hh.infrastructure.movie.MovieScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

  private final MovieScreeningRepository movieScreeningRepository;

  @Override
  @Transactional
  @Cacheable(cacheNames = "movieScreening")
  public Page<MovieScreeningDto> findMoviesWithGroupConcat(Pageable pageable, String title, Genre genre) {
    // Pageable 객체 생성 (페이지 번호: 0, 페이지 크기: 10)
    //Pageable pageable = PageRequest.of(1, 10);

    // 오늘 날짜 및 시간
    LocalDateTime today = LocalDateTime.now();

    // Service 메서드 호출
    /*List<MovieScreeningDto> result = movieScreeningRepository.findMoviesWithGroupConcat(pageable, title, genre, today);

    // 결과 출력
    for (MovieScreeningDto dto : result) {
      System.out.println(dto.toString());
    }*/
    return movieScreeningRepository.findMoviesWithGroupConcat(pageable, title, genre, today);
  }
}
