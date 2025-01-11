package com.hanghae.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Theater extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId; // 상영관 ID

    @Column(nullable = false, length = 20)
    private String theaterName; // 상영관명

    @Column(length = 255)
    private String location; // 위치
}