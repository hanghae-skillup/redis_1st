package com.example.reservation.application.service;

import com.example.reservation.application.dto.ReserveResponse;
import com.example.reservation.dao.ReservationRepository;
import com.example.reservation.presentation.dto.ReserveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReserveResponse reserveSeat(ReserveRequest reserveRequestDo) {
        return null;
    }
}
