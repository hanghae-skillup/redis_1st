package hellojpa.facade;

import hellojpa.dto.ReservationRequestDto;
import hellojpa.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptimisticLockFacade {

    private final ReservationService reservationService;

    public void reserveSeats(ReservationRequestDto reservationRequestDto) throws InterruptedException {
        while(true){
            try {
                reservationService.reserveSeats(reservationRequestDto);

                break;
            } catch (Exception e){
                Thread.sleep(50);
            }
        }

    }
}