package entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Seats {

	@Id
	@GeneratedValue
	@Column(name = "seat_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screen_id", nullable = false)
	private Screen screen;

	@Column(nullable = false)
	private String row;

	@Column(nullable = false)
	private Integer number;

	@Column(nullable = false)
	private String create_by;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime create_at;
	private String modify_by;

	@UpdateTimestamp
	private LocalDateTime modify_at;
}
