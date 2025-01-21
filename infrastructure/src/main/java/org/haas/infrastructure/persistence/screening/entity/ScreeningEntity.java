package org.haas.infrastructure.persistence.screening.entity;

import jakarta.persistence.*;
import lombok.*;
import org.haas.infrastructure.persistence.movie.entity.MovieEntity;
import org.haas.infrastructure.persistence.theater.entity.TheaterEntity;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "screenings")
@Entity
public class ScreeningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screeningId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @OneToOne
    private TheaterEntity theater;

}
