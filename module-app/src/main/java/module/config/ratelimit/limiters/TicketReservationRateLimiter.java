package module.config.ratelimit.limiters;

import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import dto.ticket.TicketReservationRequest;
import exception.ticket.NoReservationInfoException;
import exception.ticket.TwoManyReservationRequestException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TicketReservationRateLimiter implements RateLimiter {

	private final Cache<String, Boolean> blockedUser = CacheBuilder.newBuilder()
		.expireAfterWrite(5, TimeUnit.MINUTES)
		.build();

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
		return blockedUser.getIfPresent(blockKeyFormatter(user, showingId)) != null;
	}

	public void addBlock(String username, Long showingId) {
		String key = blockKeyFormatter(username, showingId);
		blockedUser.put(key, true);
	}

	public TicketReservationRequest getTicketReservationRequest(Object[] args){
		for(Object o : args){
			if(o instanceof TicketReservationRequest){
				return (TicketReservationRequest) o;
			}
		}
		throw new NoReservationInfoException();
	}

	public String blockKeyFormatter(String username, Long showingId){
		return username + "|" + showingId;
	}
}
