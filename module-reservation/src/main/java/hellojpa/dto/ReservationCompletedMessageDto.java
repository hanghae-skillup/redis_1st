package hellojpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationCompletedMessageDto {

    private final Long userId;
    private final String message;
}