package hellojpa.repository;

import hellojpa.dto.ScreeningDto;
import hellojpa.dto.SearchCondition;
import hellojpa.dto.TheaterScheduleDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ScreeningRepositoryCustom {

    List<ScreeningDto> findCurrentScreeningsMovieInfo(LocalDate todayDate, SearchCondition searchCondition);

    List<TheaterScheduleDto> findTheaterScheduleDtoByMovieTitles(List<String> titles);
}