package project.redis.application.reservation.port.inbound;

public interface ReservationCommandUseCase {

    boolean reserve(ReserveCommandParam param);
}
