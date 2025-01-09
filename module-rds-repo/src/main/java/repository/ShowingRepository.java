package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Showing;

public interface ShowingRepository extends JpaRepository<Showing,Long> {
}
