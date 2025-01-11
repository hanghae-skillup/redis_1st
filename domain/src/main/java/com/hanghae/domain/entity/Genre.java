package com.hanghae.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Genre extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId; // 장르 ID

    @Column(nullable = false, length = 50)
    private String genreName; // 장르명

    @Column(length = 200)
    private String description; // 설명
}