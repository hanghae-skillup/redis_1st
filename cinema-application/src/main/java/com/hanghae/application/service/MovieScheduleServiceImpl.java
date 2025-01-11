package com.hanghae.application.service;

import com.hanghae.application.dto.MovieScheduleDto;
import com.hanghae.application.port.in.MovieScheduleService;
import com.hanghae.application.port.out.ScreeningScheduleRepositoryPort;
import com.hanghae.domain.model.Movie;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.domain.model.UploadFile;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieScheduleServiceImpl implements MovieScheduleService {
    private final ScreeningScheduleRepositoryPort repositoryPort;

    public MovieScheduleServiceImpl(ScreeningScheduleRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    @Transactional
    public List<MovieScheduleDto> getMovieSchedules() {
        List<ScreeningSchedule> schedules = repositoryPort.findAll();

        return schedules.stream().map(schedule -> {
            Movie movie = schedule.getMovie();
            UploadFile uploadFile = movie.getUploadFile();
            String thumbnailPath = "";

            if(uploadFile != null) {
                thumbnailPath =  Optional.ofNullable(uploadFile.getFilePath()).orElse("");
                thumbnailPath =  thumbnailPath + Optional.ofNullable(uploadFile.getFileName()).orElse("");
            }

            return new MovieScheduleDto(
                    movie.getTitle(),
                    movie.getRating().getCodeName(),
                    movie.getReleaseDate(),
                    thumbnailPath,
                    movie.getRunningTime(),
                    movie.getGenre().getCodeName(),
                    schedule.getScreen().getScreenName(),
                    schedule.getShowStartDatetime()
            );
        }).collect(Collectors.toList());
    }
}
