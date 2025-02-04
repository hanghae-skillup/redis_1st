package hanghae.infrastructure.repository;

import hanghae.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaScheduleRepository extends JpaRepository<Schedule, Long> {
}
