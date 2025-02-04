package project.redis.domain.seat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import project.redis.common.exception.DataInvalidException;
import project.redis.common.exception.ErrorCode;
import project.redis.domain.cinema.Cinema;

@Getter
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Seat {
    UUID seatId;
    String seatNumber;
    Cinema cinema;

    public static Seat generateSeat(UUID seatId, String seatNumber, Cinema cinema) {
        return new Seat(seatId, seatNumber, cinema);
    }

    public char getColumn() {
        return this.seatNumber.charAt(0);
    }

    public char getRow() {
        return this.seatNumber.charAt(1);
    }

    public static boolean isAvailable(List<Seat> originSeats, List<Seat> targetSeats) {
        List<UUID> originSeatsIds = originSeats.stream().map(Seat::getSeatId).toList();

        int sameSeatCount = originSeatsIds.stream()
                .filter(seatId -> targetSeats.stream()
                        .anyMatch(seat -> seat.getSeatId() == seatId))
                .toList().size();

        if (sameSeatCount > 0) {
            throw new DataInvalidException(ErrorCode.SEAT_DUPLICATED);
        }

        if (originSeats.size() == 5 || originSeats.size() + targetSeats.size() > 5) {
            throw new DataInvalidException(ErrorCode.SEAT_EXCEED_COUNT, 5);
        }

        List<Seat> seats = new ArrayList<>();
        seats.addAll(originSeats);
        seats.addAll(targetSeats);

        return isSeries(seats);
    }

    public static boolean isSeries(List<Seat> seats) {
        if (seats == null || seats.isEmpty()) {
            return false;
        }

        char column = seats.getFirst().getColumn();

        boolean isSameColumn = seats.stream()
                .allMatch(seat -> seat.getColumn() == column);

        if (!isSameColumn) {
            throw new DataInvalidException(ErrorCode.SEAT_REQUIRED_SERIES);
        }

        List<Integer> rows = seats.stream()
                .map(Seat::getRow)
                .map(String::valueOf)
                .map(Integer::valueOf)
                .sorted()
                .toList();

        for (int i = 0; i < rows.size() - 1; i++) {
            if (i == rows.size() - 1) {
                continue;
            }
            if (rows.get(i + 1) - rows.get(i) != 1) {
                throw new DataInvalidException(ErrorCode.SEAT_REQUIRED_SERIES);
            }
        }

        return true;
    }
}
