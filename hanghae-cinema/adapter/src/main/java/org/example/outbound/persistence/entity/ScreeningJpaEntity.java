package org.example.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "screenings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieJpaEntity movieJpaEntity;

    @Column(nullable = false, length = 255)
    private String theater;

    @Column(nullable = false)
    private LocalDate showDate;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ScreeningScheduleJpaEntity> schedules = new ArrayList<>();

}
