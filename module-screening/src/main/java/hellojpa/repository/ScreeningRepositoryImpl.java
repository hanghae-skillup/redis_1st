package hellojpa.repository;

import hellojpa.domain.Screening;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
public class ScreeningRepositoryImpl implements ScreeningRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<Screening> findCurrentScreenings(LocalDate todayDate) {
        return em.createQuery("select s from Screening s " +
                "join fetch s.movie m " +
                "join fetch s.theater t " +
                "where m.releaseDate <= : todayDate " +
                "order by m.releaseDate desc, " +
                "s.startTime asc")
                .setParameter("todayDate", todayDate)
                .getResultList();
    }
}
