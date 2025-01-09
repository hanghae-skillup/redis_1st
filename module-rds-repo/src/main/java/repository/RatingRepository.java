package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
