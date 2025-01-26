package com.example.app.movie.out.persistence.entity;

import com.example.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name="tb_movie_showtime")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowtimeEntity extends BaseEntity {

    @Id
    @Column(name = "showtime_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`start`")
    private LocalTime start;

    @Column(name = "`end`")
    private LocalTime end;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;
}
