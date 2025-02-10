package hellojpa.service;

import hellojpa.domain.Movie;
import hellojpa.domain.Screening;
import hellojpa.dto.ScreeningDto;
import hellojpa.dto.TheaterScheduleDto;
import hellojpa.dto.TimeScheduleDto;
import hellojpa.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public List<ScreeningDto> findCurrentScreenings() {

        LocalDate todayDate = LocalDate.now();
        List<Screening> currentScreenings = screeningRepository.findCurrentScreenings(todayDate); // 오늘 날짜 기준 상영되는 영화 정보

        return currentScreenings.stream()
                .collect(Collectors.groupingBy(Screening::getMovie)) // 영화별 그룹화
                .entrySet().stream()
                .map(entry -> {
                    Movie movie = entry.getKey();
                    List<TheaterScheduleDto> theaterScheduleDtos = extractTheaterScheduleDto(entry.getValue());

                    return ScreeningDto.of(movie, theaterScheduleDtos);
                })
                .sorted(Comparator.comparing(ScreeningDto::getReleaseDate).reversed()) // 최근 개봉일 순 정렬
                .collect(Collectors.toList());
    }

    // screening -> TheaterScheduleDto
    private List<TheaterScheduleDto> extractTheaterScheduleDto(List<Screening> screenings) {
        return screenings.stream()
                .collect(Collectors.groupingBy(Screening::getTheater))
                .entrySet().stream()
                .map(entry -> {
                    List<TimeScheduleDto> timeScheduleDtos = extractTimeScheduleDto(entry.getValue());

                    return TheaterScheduleDto.of(entry.getKey(), timeScheduleDtos);
                })
                .collect(Collectors.toList());
    }

    // screening -> TimeScheduleDto
    private List<TimeScheduleDto> extractTimeScheduleDto(List<Screening> screenings) {
        return screenings.stream()
                .sorted(Comparator.comparing(Screening::getStartTime)) // 상영 시간 오름차순 정렬
                .map(screening -> TimeScheduleDto.of(screening))
                            .collect(Collectors.toList());
    }

}