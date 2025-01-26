package com.example.app.movie.out.persistence.entity;

import com.example.app.common.BaseEntity;
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
public class TheaterEntity extends BaseEntity {

    @Id
    @Column(name = "theater_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "theater")
    private Set<MovieTheaterEntity> movieTheaters = new HashSet<>();
}
