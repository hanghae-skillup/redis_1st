package com.hanghae.application.service;

import com.hanghae.application.dto.MovieScheduleResponseDto;
import com.hanghae.application.port.in.MovieScheduleService;
import com.hanghae.application.port.out.ScreeningScheduleRepositoryPort;
import com.hanghae.domain.model.Movie;
import com.hanghae.domain.model.ScreeningSchedule;
import com.hanghae.domain.model.UploadFile;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieScheduleServiceImpl implements MovieScheduleService {
    private final ScreeningScheduleRepositoryPort repositoryPort;

    @Override
    @Transactional
    public List<MovieScheduleResponseDto> getMovieSchedules() {
        List<ScreeningSchedule> schedules = repositoryPort.findAll();

        return schedules.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
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
