package com.example.app.movie.type;

import com.example.app.common.exception.APIException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.example.app.booking.exception.BookingErrorMessage.SEAT_ROW_NOT_IN_SEQUENCE;

@Getter
@RequiredArgsConstructor
public enum TheaterSeat {
    A1,A2,A3,A4,A5,
    B1,B2,B3,B4,B5,
    C1,C2,C3,C4,C5,
    D1,D2,D3,D4,D5,
    E1,E2,E3,E4,E5;

    public static String getRow(TheaterSeat theaterSeat) {
        return theaterSeat.name().substring(0, 1);
    }

    public static void checkSeatsInSequence(Set<TheaterSeat> theaterSeats) {
        String firstRow = TheaterSeat.getRow(theaterSeats.iterator().next());
        for (TheaterSeat theaterSeat : theaterSeats) {
            if (!TheaterSeat.getRow(theaterSeat).equals(firstRow)) {
                throw new APIException(SEAT_ROW_NOT_IN_SEQUENCE);
            }
        }
    }
}
