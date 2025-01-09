package entity;

import java.time.LocalDate;

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
public class Movie {

	@Id
	@GeneratedValue
	@Column(name = "movie_id")
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private LocalDate openDay;

	@Column(nullable = false)
	private Integer runningTime;
	private String thumbnail;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genre_id", nullable = false)
	private Genre genre;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rating_id", nullable = false)
	private Rating rating;
	private String plot;

	@Column(nullable = false)
	private String create_by;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDate create_at;
	private String modify_by;

	@UpdateTimestamp
	private LocalDate modify_at;
}
