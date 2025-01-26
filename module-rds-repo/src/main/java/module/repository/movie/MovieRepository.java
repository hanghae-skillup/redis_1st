package module.repository.movie;

import org.springframework.data.jpa.repository.JpaRepository;

import module.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
