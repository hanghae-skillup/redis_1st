package module.repository.seats;

import org.springframework.data.jpa.repository.JpaRepository;

import module.entity.Seats;

public interface SeatsRepository extends JpaRepository<Seats, Long> {
}
