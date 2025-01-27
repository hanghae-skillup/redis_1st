package hellojpa.controller;

import hellojpa.dto.ReservationDto;
import hellojpa.dto.ScreeningDto;
import hellojpa.dto.SearchCondition;
import hellojpa.service.ReservationService;
import hellojpa.service.ScreeningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ScreeningController {

    private final ScreeningService screeningService;
    private final ReservationService reservationService;

    @GetMapping("/screening/movies")
    public List<ScreeningDto> getCurrentScreenings(@Valid @ModelAttribute SearchCondition searchCondition) {
        log.info("ModelAttribute.title: {}", searchCondition.getTitle());
        log.info("ModelAttribute.genre: {}", searchCondition.getGenre());

        return screeningService.findCurrentScreenings(LocalDate.now(), searchCondition);
    }

    @PostMapping("/reservation/movie")
    public ResponseEntity<String> reserveSeats(@Valid @RequestBody ReservationDto requestDto) {
        reservationService.reserveSeats(requestDto);
        return ResponseEntity.ok("좌석 예약이 완료되었습니다.");
    }

}