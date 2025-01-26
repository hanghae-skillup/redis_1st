package com.example.api.screening.mapper;

import com.example.api.screening.dto.ScreeningResponseDto;
import com.example.domain.screening.dto.ProjectionScreeningResponseDto;
import com.example.domain.screening.entity.Screening;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScreeningDtoMapper {

    public static List<ScreeningResponseDto> toScreeningResponseDto(List<ProjectionScreeningResponseDto> projections) {

        return projections.stream()
                .collect(Collectors.groupingBy(ProjectionScreeningResponseDto::getTitle))
                .entrySet()
                .stream()
                .map(entry -> {
                    String title = entry.getKey();
                    List<ProjectionScreeningResponseDto> screenings = entry.getValue();

                    ProjectionScreeningResponseDto first = screenings.get(0);
                    return ScreeningResponseDto.builder()
                            .title(title)
                            .thumbnailUrl(first.getThumbnailUrl())
                            .rating(first.getRating().toString())
                            .releaseDate(first.getReleaseDate())
                            .runtimeMinutes(first.getRuntimeMinutes())
                            .genre(first.getGenre().toString())
                            .theaterSchedules(
                                    screenings.stream()
                                            .collect(Collectors.groupingBy(ProjectionScreeningResponseDto::getTheaterName))
                                            .entrySet()
                                            .stream()
                                            .map(theaterEntry -> ScreeningResponseDto.TheaterSchedule.builder()
                                                    .theaterName(theaterEntry.getKey())
                                                    .schedules(theaterEntry.getValue().stream()
                                                            .map(s -> new ScreeningResponseDto.TheaterSchedule.Schedule(
                                                                    s.getStartTime().toLocalTime(),
                                                                    s.getEndTime().toLocalTime()
                                                            ))
                                                            .collect(Collectors.toList()))
                                                    .build())
                                            .collect(Collectors.toList())
                            )
                            .build();
                })
                .sorted(Comparator.comparing(ScreeningResponseDto::getReleaseDate).reversed())
                .collect(Collectors.toList());
    }

}
