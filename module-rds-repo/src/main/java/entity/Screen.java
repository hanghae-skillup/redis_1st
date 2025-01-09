package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Screen {

	@Id
	@GeneratedValue
	@Column(name = "screen_id")
	private Long screen_id;

	@OneToMany(mappedBy = "screen")
	private List<Seats> seats = new ArrayList<>();

	private String name;

	@Column(nullable = false)
	private String create_by;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime create_at;
	private String modify_by;

	@UpdateTimestamp
	private LocalDateTime modify_at;
}
