package hellojpa.repository;

import hellojpa.domain.Screening;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningRepositoryCustom {

    List<Screening> findCurrentScreenings(LocalDate todayDate);
}
