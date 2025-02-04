package hanghae.application.service;

import hanghae.application.port.SeatService;
import hanghae.domain.entity.Seat;
import hanghae.domain.port.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    public Seat findSeatBySeatNameAndScreenId(String seatName, Long screenId) {
        return seatRepository.findSeatBySeatNameAndScreenId(seatName, screenId)
                .orElseThrow(() -> new IllegalArgumentException("No seat found with name: " + seatName));
    }
}
