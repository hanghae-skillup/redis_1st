package com.example.app.movie.out.persistence.entity;

import com.example.app.movie.common.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_movie_theater_rel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieTheaterJpaEntity extends BaseJpaEntity {

    @Id
    @Column(name = "movie_theater_rel_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieJpaEntity movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private TheaterJpaEntity theater;
}
