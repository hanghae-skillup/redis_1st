package com.movie.storage.movie.repository;

import com.movie.domain.movie.ReservationRepository;
import com.movie.domain.movie.domain.Reservation;
import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.storage.mapper.ModelMapper;
import com.movie.storage.movie.dto.payload.ReservationPayload;
import com.movie.storage.movie.entity.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaQuerySupport reservationJpaQuerySupport;

    @Override
    public List<Reservation> getReservations(ReservationCommand.Get get) {
        List<ReservationPayload.Get> reservations = reservationJpaQuerySupport.getReservations(get.schedule(), get.seatIds());
        return reservations.stream().map(ModelMapper.ReservationMapper::fromPayload).toList();
    }

    @Override
    public void makeReservation(ReservationCommand.Reserve reserve) {
        List<ReservationEntity> reservations = reservationJpaQuerySupport.getReservationEntities(reserve.scheduleId(), reserve.seatIds());
        reservations.forEach(reservation -> {
            reservation.makeReservation(reserve.userId());
        });
    }

}
