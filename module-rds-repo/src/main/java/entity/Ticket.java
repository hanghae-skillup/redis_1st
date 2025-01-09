package entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket {

	@Id
	@GeneratedValue
	@Column(name = "ticket_id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "showing_id",nullable = false)
	private Showing showing;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seat_id", nullable = false)
	private Seats seats;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TicketStatus ticketStatus;

	@Column(nullable = false)
	private String create_by;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime create_at;
	private String modify_by;

	@UpdateTimestamp
	private LocalDateTime modify_at;
}
