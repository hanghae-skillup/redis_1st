package com.movie.storage.mapper;

import com.movie.common.enums.AxisY;
import com.movie.domain.movie.domain.*;
import com.movie.domain.userAccount.UserAccount;
import com.movie.storage.movie.dto.payload.ReservationPayload;
import com.movie.storage.movie.entity.*;
import com.movie.storage.userAccount.UserAccountEntity;

import java.time.LocalDateTime;

public class ModelMapper {

    public record UserAccountMapper(Long id, String name, String token) {
        public static UserAccount from(UserAccountEntity entity) {
            return UserAccount.of(entity.getId(), entity.getName(), entity.getToken());
        }

        public static UserAccountMapper of(Long id, String name, String token) {
            return new UserAccountMapper(id, name, token);
        }
    }

    public record SeatMapper(Long id, String seatNumber, AxisY axisY, Integer axisX) {
        public static SeatMapper of(Long id, String seatNumber, AxisY axisY, Integer axisX) {
            return new SeatMapper(id, seatNumber, axisY, axisX);
        }

        public static Seat from(SeatEntity entity) {
            return Seat.of(
                    entity.getId(), entity.getSeatNumber(),
                    entity.getAxisY(), entity.getAxisX()
            );
        }
    }

    public record ReservationMapper(Long scheduleId, Long seatId, Long userId, LocalDateTime reservedAt) {
        public static Reservation from(ReservationEntity entity) {
            return Reservation.of(
                    entity.getId().getScheduleId(), entity.getId().getSeatId(),
                    entity.getUserId(), entity.getReservedAt()
            );
        }

        public static Reservation fromPayload(ReservationPayload.Get get) {
            return Reservation.of(
                    get.getScheduleId(), get.getSeatId(),
                    get.getUserId(), get.getReservedAt()
            );
        }
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
