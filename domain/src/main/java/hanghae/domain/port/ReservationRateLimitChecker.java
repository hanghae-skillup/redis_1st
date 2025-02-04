package hanghae.domain.port;

public interface ReservationRateLimitChecker {

    void oneReservationPerFiveMinutes(String key);
}
