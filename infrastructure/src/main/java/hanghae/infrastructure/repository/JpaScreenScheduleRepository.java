package hanghae.infrastructure.repository;

import hanghae.domain.entity.ScreenSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaScreenScheduleRepository extends JpaRepository<ScreenSchedule, Long> {
}
