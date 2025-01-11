package com.dpflsy.movie.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // "15세 이상 관람가" 등
}