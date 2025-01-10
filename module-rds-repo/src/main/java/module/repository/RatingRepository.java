package module.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import module.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
