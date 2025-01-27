package com.movie.application.service;

import com.movie.domain.entity.Reservation;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public void sendReservationComplete(Reservation reservation) {
        // TODO: Implement actual message sending logic
        System.out.println("Reservation complete notification sent for reservation: " + reservation.getReservationNumber());
    }
} 