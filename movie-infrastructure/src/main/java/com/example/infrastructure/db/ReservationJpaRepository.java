package com.example.infrastructure.db;

import com.example.domain.model.entity.Member;
import com.example.domain.model.entity.Reservation;
import com.example.domain.model.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Long> {
    int countByScreeningAndMember(Screening screening, Member member);
}
