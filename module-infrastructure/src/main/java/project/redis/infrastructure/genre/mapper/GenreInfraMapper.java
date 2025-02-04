package project.redis.infrastructure.genre.mapper;

import project.redis.domain.genre.Genre;
import project.redis.infrastructure.genre.entity.GenreJpaEntity;

public class GenreInfraMapper {

    public static Genre toGenre(GenreJpaEntity genre) {
        return Genre.generateGenre(
                genre.getId(),
                genre.getGenreName()
        );
    }

    public static GenreJpaEntity toEntity(Genre genre) {
        return GenreJpaEntity.builder()
                .id(genre.getGenreId())
                .genreName(genre.getGenreName())
                .build();
    }
}
