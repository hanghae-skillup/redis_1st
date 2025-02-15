package module.entity;

import java.time.LocalDate;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Long movieId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private LocalDate openDay;

	@Column(nullable = false)
	private Integer runningTimeAsMinute;
	private String thumbnail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "genre_id", nullable = false)
	private Genre genre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rating_id", nullable = false)
	private Rating rating;

	@Column(columnDefinition = "TEXT")
	private String plot;
}
