package hellojpa.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {

    @NotNull(message = "User id는 필수입니다.")
    private Long userId;

    @NotNull(message = "Screening id는 필수입니다.")
    private Long screeningId;

    @NotEmpty(message = "예약 좌석은 비어 있을 수 없습니다.")
    @Size(max = 5, message = "최대 5개의 좌석만 예약할 수 있습니다.")
    private List<Long> reservationSeatsId;

}