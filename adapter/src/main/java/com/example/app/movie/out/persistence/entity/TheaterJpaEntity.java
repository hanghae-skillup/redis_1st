package com.example.app.movie.out.persistence.entity;

import com.example.app.movie.common.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tb_theater")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterJpaEntity extends BaseJpaEntity {

    @Id
    @Column(name = "theater_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "theater")
    private Set<MovieTheaterJpaEntity> movieTheaters = new HashSet<>();
}
