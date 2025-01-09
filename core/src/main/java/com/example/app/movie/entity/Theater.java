package com.example.app.movie.entity;

import com.example.app.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_theater")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theater extends BaseEntity {

    @Id
    @Column(name = "theater_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "theaters")
    private List<Movie> movies = new ArrayList<>();
}
