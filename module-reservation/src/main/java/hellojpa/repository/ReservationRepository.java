package hellojpa.repository;

import hellojpa.domain.Reservation;
import hellojpa.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select s from Seat s join fetch s.reservation r where r.screening.id = :screeningId")
    List<Seat> findReservedSeatsByScreeningId(@Param("screeningId") Long screeningId);

}