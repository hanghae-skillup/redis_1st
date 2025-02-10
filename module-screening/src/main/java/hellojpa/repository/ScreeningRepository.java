package hellojpa.repository;

import hellojpa.domain.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Long>, ScreeningRepositoryCustom {
}
