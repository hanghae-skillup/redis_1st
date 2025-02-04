package hanghae.infrastructure.repository;

import hanghae.domain.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaScreenRepository extends JpaRepository<Screen, Long> {
}
