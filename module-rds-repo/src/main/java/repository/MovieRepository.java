package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Movie;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
