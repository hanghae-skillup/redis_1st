package com.example.infrastructure.db;

import com.example.domain.model.entity.Movie;
import com.example.domain.model.projection.MovieProjection;
import com.example.domain.model.valueObject.Genre;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieJpaRepository extends JpaRepository<Movie, Long> {

    @Query("""
    SELECT m 
    FROM Movie m 
    WHERE (:title IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))) 
      AND (:genre IS NULL OR m.genre = :genre)
""")
    List<MovieProjection> findByTitleContainingIgnoreCaseAndGenre(
            @Param("title") String title,
            @Param("genre") Genre genre,
            Sort sort
    );

}
