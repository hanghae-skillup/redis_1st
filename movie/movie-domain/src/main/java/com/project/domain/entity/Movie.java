package com.project.domain.entity;

import com.project.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String movieId;
    private String movieName;
    private Enum movieGrade;
    private LocalDateTime movieReleaseDate;
    private String movieImageUrl;
    private Long runningTime;
    private Enum MovieGenre;

}
