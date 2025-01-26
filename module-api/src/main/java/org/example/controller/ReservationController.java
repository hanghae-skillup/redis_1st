package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.ReservationRequestDto;
import org.example.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/reservation")
    public ResponseEntity<String> getPlayingMovies(@RequestBody @Validated ReservationRequestDto reservationRequestDto) {
        reservationService.reserveMovie(reservationRequestDto);
        return ResponseEntity.ok("예약이 완료되었습니다.");
    }

}
