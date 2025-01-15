package com.hanghae.application.service;

import com.hanghae.application.dto.MovieScheduleResponseDto;
import com.hanghae.application.dto.ShowingMovieScheduleResponseDto;
import com.hanghae.application.port.in.MovieScheduleService;
import com.hanghae.application.port.out.MovieRepositoryPort;
import com.hanghae.application.port.out.ScreeningScheduleRepositoryPort;
import com.hanghae.application.projection.MovieScheduleProjection;
import com.hanghae.domain.model.Movie;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.domain.model.UploadFile;
import com.hanghae.domain.model.enums.MovieGenre;
import com.hanghae.domain.model.enums.MovieRating;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.sql.Date;

@Service
@RequiredArgsConstructor
public class MovieScheduleServiceImpl implements MovieScheduleService {
    private final ScreeningScheduleRepositoryPort screeningScheduleRepositoryPort;
    private final MovieRepositoryPort movieRepositoryPort;

    @Override
    @Transactional
    public List<MovieScheduleResponseDto> getMovieSchedules() {
        List<ScreeningSchedule> schedules = screeningScheduleRepositoryPort.findAll();

        return schedules.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ShowingMovieScheduleResponseDto> getShowingMovieSchedules() {
        List<MovieScheduleProjection> projections = movieRepositoryPort.findShowingMovieSchedules();

        Map<Long, ShowingMovieScheduleResponseDto> movieMap = new LinkedHashMap<>();

        for (MovieScheduleProjection projection : projections) {
            Long movieId = projection.getId();

            // 영화가 이미 맵에 존재하는지 확인, 없다면 생성
            ShowingMovieScheduleResponseDto movie = movieMap.computeIfAbsent(movieId, id -> ShowingMovieScheduleResponseDto.builder()
                    .movieId(projection.getId())
                    .title(projection.getTitle())
                    .rating(projection.getRating().getCodeName())
                    .releaseDate(projection.getReleaseDate())
                    .thumbnail(projection.getThumbnail())
                    .runningTimeMinutes(projection.getRunningTimeMinutes())
                    .genre(projection.getGenre().getCodeName())
                    .build()
            );

            Long runningTimeMinutes = movie.getRunningTimeMinutes();
            LocalDateTime showStartAt = projection.getShowStartAt();
            LocalDateTime showEndAt = null;

            if(showStartAt != null) {// 상영시작시간 존재하면 종료시간 계산
                showEndAt = showStartAt.minusMinutes(runningTimeMinutes);
            }

            // 스케줄 추가
            movie.addSchedule(ShowingMovieScheduleResponseDto.Schedule.builder()
                    .screenName(projection.getScreenName())
                    .showStartAt(projection.getShowStartAt())
                    .showEndAt(showEndAt)
                    .build());
        }

        return new ArrayList<>(movieMap.values());
    }

    private MovieScheduleResponseDto convertToDto(ScreeningSchedule schedule) {
        Movie movie = schedule.getMovie();
        String thumbnailPath = getThumbnailPath(movie.getUploadFile());

        return new MovieScheduleResponseDto(
                movie.getTitle(),
                movie.getRating().getCodeName(),
                movie.getReleaseDate(),
                thumbnailPath,
                movie.getRunningTimeMinutes(),
                movie.getGenre().getCodeName(),
                schedule.getScreen().getScreenName(),
                schedule.getShowStartAt()
        );
    }

    private String getThumbnailPath(UploadFile uploadFile) {
        if (uploadFile == null) {
            return "";
        }
        String filePath = Optional.ofNullable(uploadFile.getFilePath()).orElse("");
        String fileName = Optional.ofNullable(uploadFile.getFileName()).orElse("");
        return filePath + fileName;
    }
}
