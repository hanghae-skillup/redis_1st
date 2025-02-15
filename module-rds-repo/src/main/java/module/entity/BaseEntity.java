package module.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
	@Column(nullable = false)
	private String createBy;

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createAt;
	private String modifyBy;

	@UpdateTimestamp
	private LocalDateTime modifyAt;
}
