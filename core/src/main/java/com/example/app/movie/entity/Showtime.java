package com.example.app.movie.entity;

import com.example.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="tb_movie_showtime")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Showtime extends BaseEntity {

    @Id
    @Column(name = "showtime_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "movie_id")
    private Long movieId;

    private String start;

    private String end;
}
