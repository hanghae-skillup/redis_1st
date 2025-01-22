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

    @Cacheable(value = "screenings", key = "#searchCondition.title == null ? 'EMPTY' : #searchCondition.title " +
            "+ (#searchCondition.genre == null ? 'EMPTY' : #searchCondition.genre)")
    public List<ScreeningDto> findCurrentScreenings(LocalDate todayDate, SearchCondition searchCondition) {

        List<ScreeningDto> screeningDtos = screeningRepository.findCurrentScreenings(todayDate, searchCondition);

        // screeningDtos에 상영관과 시간 스케줄을 추가
        addTheaterScheduleDtoToScreenings(screeningDtos);

        return screeningDtos;
    }

    private void addTheaterScheduleDtoToScreenings(List<ScreeningDto> screeningDtos) {

        for (ScreeningDto screeningDto : screeningDtos) {
            // 상영관 스케줄 조회
            List<TheaterScheduleDto> theaterScheduleDtos =
                    screeningRepository.findTheaterScheduleDtoByMovieTitle(screeningDto.getTitle());

            // 상영관마다 상영 스케줄 삽입
            Map<String, List<TimeScheduleDto>> theaterSchedulesMap = new HashMap<>();
            for (TheaterScheduleDto theaterScheduleDto : theaterScheduleDtos) {
                String theaterName = theaterScheduleDto.getName();
                theaterSchedulesMap.putIfAbsent(theaterName, new ArrayList<>());
                theaterSchedulesMap.get(theaterName).addAll(theaterScheduleDto.getTimeScheduleDtoList());
            }

            // theaterSchedulesMap -> finalTheaterScheduleDtos
            List<TheaterScheduleDto> finalTheaterScheduleDtos = new ArrayList<>();
            for (Map.Entry<String, List<TimeScheduleDto>> entry : theaterSchedulesMap.entrySet()) {
                finalTheaterScheduleDtos.add(new TheaterScheduleDto(entry.getKey(), entry.getValue()));
            }

            screeningDto.getTheaterSheduleDtoList().clear();
            screeningDto.getTheaterSheduleDtoList().addAll(finalTheaterScheduleDtos);
        }
    }
}