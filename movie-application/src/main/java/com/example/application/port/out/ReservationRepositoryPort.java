package com.example.application.port.out;

import com.example.domain.model.entity.Member;
import com.example.domain.model.entity.Reservation;
import com.example.domain.model.entity.Screening;
import java.util.Optional;

public interface ReservationRepositoryPort {
    void save(Reservation reservation);
    Optional<Reservation> findById(Long id);
    void deleteAll();
    int countByScreeningAndMember(Screening screening, Member member);
}
