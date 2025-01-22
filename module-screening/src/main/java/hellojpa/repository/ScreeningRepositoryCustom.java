package hellojpa.repository;

import hellojpa.dto.ScreeningDto;
import hellojpa.dto.SearchCondition;
import hellojpa.dto.TheaterScheduleDto;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningRepositoryCustom {

    List<ScreeningDto> findCurrentScreenings(LocalDate todayDate, SearchCondition searchCondition);

    List<TheaterScheduleDto> findTheaterScheduleDtoByMovieTitle(String title);
}