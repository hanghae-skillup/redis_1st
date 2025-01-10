package module.entity;

public enum TicketStatus {
	ON_SALE, RESERVED, COMPLETED;

	public String getName() {
		return this.name().toString();
	}
}
