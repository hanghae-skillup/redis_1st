package com.example.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theater extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "theater")
    private List<MovieTheater> movieTheaters = new ArrayList<>();

    public Theater(String name) {
        this.name = name;
    }
}
