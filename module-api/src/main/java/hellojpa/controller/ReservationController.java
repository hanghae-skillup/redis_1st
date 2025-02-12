package hellojpa.controller;

import hellojpa.dto.RateLimitResponseDto;
import hellojpa.dto.ReservationRequestDto;
import hellojpa.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservation/movie")
    public ResponseEntity<RateLimitResponseDto<String>> reserveSeats(@Valid @RequestBody ReservationRequestDto requestDto) {
        reservationService.reserveSeats(requestDto);
        return ResponseEntity.ok(RateLimitResponseDto.success(null));
    }
}