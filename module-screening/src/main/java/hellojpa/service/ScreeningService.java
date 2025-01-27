package hellojpa.service;

import hellojpa.domain.Movie;
import hellojpa.domain.Screening;
import hellojpa.dto.ScreeningDto;
import hellojpa.dto.SearchCondition;
import hellojpa.dto.TheaterScheduleDto;
import hellojpa.dto.TimeScheduleDto;
import hellojpa.repository.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    /*@Cacheable(value = "currentScreenings", key = "#todayDate + '-' + " +
            "(#searchCondition.title != null ? #searchCondition.title : 'all') + '-' + " +
            "(#searchCondition.genre != null ? #searchCondition.genre : 'all')")*/
    public List<ScreeningDto> findCurrentScreenings(LocalDate todayDate, SearchCondition searchCondition) {

        // 영화 기본 정보 조회
        List<ScreeningDto> screeningDtos = screeningRepository.findCurrentScreeningsMovieInfo(todayDate, searchCondition);

        // 영화 제목 리스트 추출
        List<String> titles = screeningDtos.stream()
                .map(ScreeningDto::getTitle)
                .collect(Collectors.toList());

        // 상영관, 상영 시간 정보 조회
        List<TheaterScheduleDto> theaterScheduleDtos = screeningRepository.findTheaterScheduleDtoByMovieTitles(titles);

        // 영화 제목 기준 그룹화
        Map<String, List<TheaterScheduleDto>> theaterScheduleMap = theaterScheduleDtos.stream()
                .collect(Collectors.groupingBy(TheaterScheduleDto::getTitle));

        for (ScreeningDto screeningDto : screeningDtos) {
            // 해당 영화의 상영관 목록 가져오기
            List<TheaterScheduleDto> schedules = theaterScheduleMap.getOrDefault(screeningDto.getTitle(), Collections.emptyList());

            // 상영관을 이름으로 그룹화하여 상영 시간 조회
            Map<String, List<TimeScheduleDto>> groupedSchedules = schedules.stream()
                    .collect(Collectors.groupingBy(TheaterScheduleDto::getName,
                            Collectors.flatMapping(theaterSchedule -> theaterSchedule.getTimeScheduleDtoList().stream(), Collectors.toList())));

            screeningDto.getTheaterSheduleDtoList().clear();

            for (Map.Entry<String, List<TimeScheduleDto>> entry : groupedSchedules.entrySet()) {
                TheaterScheduleDto combinedTheaterSchedule = new TheaterScheduleDto(screeningDto.getTitle(), entry.getKey(), entry.getValue());
                screeningDto.getTheaterSheduleDtoList().add(combinedTheaterSchedule);
            }
        }

        return screeningDtos;
    }
}