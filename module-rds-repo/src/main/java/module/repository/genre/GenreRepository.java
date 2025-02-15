package module.repository.genre;

import org.springframework.data.jpa.repository.JpaRepository;

import module.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
