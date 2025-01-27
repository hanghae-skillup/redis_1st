package com.example.domain.seatReservation.service;

import com.example.domain.screening.entity.Screening;
import com.example.domain.seatReservation.entity.SeatReservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SeatReservationService {
    private final SeatReservationRepository seatReservationRepository;

    @Transactional(readOnly = true)
    public SeatReservation getSeatReservationInfo(Long seatReservationId) {
        return seatReservationRepository.getSeatReservationInfo(seatReservationId);
    }

    @Transactional(readOnly = true)
    public List<SeatReservation> getSeatReservationInfoByUser(Long userId) {
        return seatReservationRepository.getSeatReservationInfoByUser(userId);
    }


    public Boolean reserve(int count, Long userId, String seatNumber, Screening screening) {
        Integer userCount = getSeatReservationInfoByUser(userId).size();

        if(!checkCount(count,userCount)){
            return false;
        }

        List<String> seats = getSeats(seatNumber,count);

        if(!checkReserveAble(seats)){
            return false;
        }
        reserveSeats(SeatReservation.makeReservation(count, seats, screening,userId));

        return true;
    }

    @Transactional(readOnly = true)
    public boolean checkCount(Integer count, Integer reservedCount){
        return count+reservedCount<=5;

    }
    @Transactional(readOnly = true)
    public List<String> getSeats(String seatNumber, Integer count){
        List<String> seatNumberList = new ArrayList<>();
        char row = seatNumber.charAt(0);
        int startNumber = Integer.parseInt(seatNumber.substring(1));

        for (int i = 0; i < count; i++) {
            seatNumberList.add(row + String.valueOf(startNumber + i));
        }

        return seatNumberList;
    }

    @Transactional(readOnly = true)
    public Boolean checkReserveAble(List<String> seats) {
        return seatReservationRepository.checkReserveAble(seats);
    }

    public void reserveSeats(List<SeatReservation> seatReservations){
        seatReservationRepository.saveSeats(seatReservations);
    }


}
