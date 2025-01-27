package module.entity;

import dto.ticket.TicketStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Long ticketId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "showing_id",nullable = false)
	private Showing showing;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seat_id", nullable = false)
	private Seats seats;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TicketStatus ticketStatus;

	@Version
	private Integer version;

	public void setTicketStatus(TicketStatus ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
}
