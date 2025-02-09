package dto.ticket;

public enum TicketStatus {
	ON_SALE, ON_RESERVING, RESERVED;

	public String getName() {
		return this.name().toString();
	}
}
