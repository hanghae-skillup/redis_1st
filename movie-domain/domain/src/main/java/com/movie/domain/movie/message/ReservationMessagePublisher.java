package com.movie.domain.movie.message;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMessagePublisher {

    private final ApplicationEventPublisher publisher;

    public void sendMessage(ReservationMessage.Send send) {
        publisher.publishEvent(send);
    }

}
