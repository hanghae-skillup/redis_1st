package hellojpa.repository;

import hellojpa.domain.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Seat s where s.id in :ids")
    List<Seat> findByIdWithPessimisticLock(@Param("ids") List<Long> ids);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select s from Seat s where s.id in :ids")
    List<Seat> findByIdWithOptimisticLock(@Param("ids") List<Long> ids);
}