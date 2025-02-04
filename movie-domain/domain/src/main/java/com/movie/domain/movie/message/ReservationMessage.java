package com.movie.domain.movie.message;

public class ReservationMessage {

    public record Send(String message) {
        public static Send of(String message) {
            return new Send(message);
        }
    }

}
