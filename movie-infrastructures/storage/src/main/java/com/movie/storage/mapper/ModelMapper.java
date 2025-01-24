package com.movie.storage.mapper;

import com.movie.domain.movie.domain.Movie;
import com.movie.domain.movie.domain.Schedule;
import com.movie.domain.movie.domain.Screen;
import com.movie.domain.movie.domain.Theater;
import com.movie.domain.userAccount.UserAccount;
import com.movie.storage.movie.entity.MovieEntity;
import com.movie.storage.movie.entity.ScheduleEntity;
import com.movie.storage.movie.entity.ScreenEntity;
import com.movie.storage.movie.entity.TheaterEntity;
import com.movie.storage.userAccount.UserAccountEntity;

public class ModelMapper {

    public static UserAccount from(UserAccountEntity entity) {
        return UserAccount.of(entity.getId(), entity.getName(), entity.getToken());
    }

    public static Schedule from(ScheduleEntity scheduleEntity) {
        return null;
    }

    public static Movie from(MovieEntity entity) {
        return Movie.of(
                entity.getId(), entity.getTitle(),
                entity.getReleasedAt(), entity.getThumbnailUrl(),
                entity.getRunningTime(), entity.getFilmRating(),
                entity.getGenre()
        );
    }

    public static Screen from(ScreenEntity screenEntity) {
        return Screen.of(
                screenEntity.getId(), screenEntity.getName(),
                screenEntity.getTheaterId()
        );
    }

    public static Theater from(TheaterEntity entity) {
        return Theater.of(entity.getId(), entity.getName());
    }

}
