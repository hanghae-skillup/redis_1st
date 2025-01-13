package com.example.app.movie.out.persistence.entity;

import com.example.app.movie.common.BaseJpaEntity;
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
public class ShowtimeJpaEntity extends BaseJpaEntity {

    @Id
    @Column(name = "showtime_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalTime start;

    private LocalTime end;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieJpaEntity movie;
}
