package com.movie.storage.movie.repository;

import com.movie.domain.movie.ReservationRepository;
import com.movie.domain.movie.domain.Reservation;
import com.movie.domain.movie.dto.command.ReservationCommand;
import com.movie.storage.mapper.ModelMapper;
import com.movie.storage.movie.entity.ReservationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public List<Reservation> getReservations(ReservationCommand.Get get) {
        List<ReservationEntity> reservations =
                reservationJpaRepository.findById_ScheduleIdAndId_SeatIdIn(get.schedule(), get.seatIds());
        return reservations.stream().map(ModelMapper.ReservationMapper::from).toList();
    }

    @Override
    @Transactional
    public void makeReservation(ReservationCommand.Reserve reserve) {
        reservationJpaRepository.updateAsReserved(reserve.scheduleId(), reserve.seatIds(), reserve.userId(), LocalDateTime.now());
    }

}
