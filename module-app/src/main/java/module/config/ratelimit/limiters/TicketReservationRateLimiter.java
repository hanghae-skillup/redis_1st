package module.config.ratelimit.limiters;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import dto.ticket.TicketReservationRequest;
import exception.ticket.NoReservationInfoException;
import exception.ticket.TwoManyReservationRequestException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketReservationRateLimiter implements RateLimiter {

	private final String KEY_PREFIX = "hanghaeho:ticket_reservation_complete:";
	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public boolean preCheck(Object[] args, ContentCachingRequestWrapper request) {
		TicketReservationRequest ticketReservationRequest = getTicketReservationRequest(args);
		Long showingId = ticketReservationRequest.getShowingId();
		String username = ticketReservationRequest.getUsername();

		if(isBlocked(username,showingId)){
			throw new TwoManyReservationRequestException();
		} else {
			return true;
		}
	}

	@Override
	public void postCheck(Object[] args, ContentCachingResponseWrapper response) {
		if(response.getStatus() != HttpStatus.OK.value()){
			return;
		}

		TicketReservationRequest ticketReservationRequest = getTicketReservationRequest(args);

		Long showingId = ticketReservationRequest.getShowingId();
		String username = ticketReservationRequest.getUsername();

		addBlock(username, showingId);
	}

	public boolean isBlocked(String user, Long showingId) {
		String key = keyFormatter(user, showingId);
		return redisTemplate.opsForValue().get(key) != null;
	}

	public void addBlock(String username, Long showingId) {
		String key = keyFormatter(username, showingId);
		redisTemplate.opsForValue().set(key, "complete", 300000);
	}

	public TicketReservationRequest getTicketReservationRequest(Object[] args){
		for(Object o : args){
			if(o instanceof TicketReservationRequest){
				return (TicketReservationRequest) o;
			}
		}
		throw new NoReservationInfoException();
	}

	public String keyFormatter(String username, Long showingId){
		return KEY_PREFIX + username + ":" + showingId;
	}
}
