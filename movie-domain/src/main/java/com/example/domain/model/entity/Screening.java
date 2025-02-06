package com.example.domain.model.entity;

import com.example.domain.model.base.AuditingFields;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalTime;
import java.util.List;
import lombok.Getter;

@Getter
@Entity
public class Screening extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private LocalTime startTime;
    @Column(nullable = false) private LocalTime endTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "screening", cascade = CascadeType.ALL)
    private List<ScreeningSeat> screeningSeats;


    protected Screening() {}

    private Screening(LocalTime startTime, Movie movie, Theater theater) {
        this.startTime = startTime;
        this.endTime = calculateEndTime(movie.getRuntimeMinutes());
        this.movie = movie;
        this.theater = theater;
    }

    public static Screening of(LocalTime startTime, Movie movie, Theater theater) {
        return new Screening(startTime, movie, theater);
    }

    private LocalTime calculateEndTime(int runtimeMinutes) {
        return this.startTime.plusMinutes(runtimeMinutes);
    }

}
