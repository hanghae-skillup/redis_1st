package com.movie.domain.repository;

import com.movie.domain.dto.MovieSearchCondition;
import com.movie.domain.entity.Movie;
import com.movie.domain.repository.MovieRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Movie> findNowShowingMovies(MovieSearchCondition condition) {
        return em.createQuery(
                "select m from Movie m " +
                        "where m.releaseDate <= :now",
                Movie.class)
                .setParameter("now", condition.getSearchDate())
                .getResultList();
    }
} 