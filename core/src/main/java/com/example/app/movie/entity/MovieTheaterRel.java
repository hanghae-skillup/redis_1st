package com.example.app.movie.entity;

import com.example.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tb_movie_theater_rel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieTheaterRel extends BaseEntity {

    @Id
    @Column(name = "movie_theater_rel_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "theater_id")
    private Long theaterId;
}
