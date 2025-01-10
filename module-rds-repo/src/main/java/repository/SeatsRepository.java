package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Seats;

public interface SeatsRepository extends JpaRepository<Seats, Long> {
}
