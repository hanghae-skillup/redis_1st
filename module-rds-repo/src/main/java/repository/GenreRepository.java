package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Genre;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
