package hellojpa.facade;

import hellojpa.dto.ReservationDto;
import hellojpa.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptimisticLockFacade {

    private final ReservationService reservationService;

    public void reserveSeats(ReservationDto reservationDto) throws InterruptedException {
        while(true){
            try {
                reservationService.reserveSeats(reservationDto);

                break;
            } catch (Exception e){
                Thread.sleep(50);
            }
        }

    }
}