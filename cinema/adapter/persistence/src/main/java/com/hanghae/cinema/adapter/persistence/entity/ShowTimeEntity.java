package com.hanghae.cinema.adapter.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_showtime")
public class ShowTimeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private TheaterEntity theater;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
