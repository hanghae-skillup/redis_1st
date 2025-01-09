package entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GENRE")
public class Genre {

	@Id
	@GeneratedValue
	@Column(name = "genre_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String createBy;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createAt;

	private String modifyBy;

	@UpdateTimestamp
	private LocalDateTime modifyAt;

}

