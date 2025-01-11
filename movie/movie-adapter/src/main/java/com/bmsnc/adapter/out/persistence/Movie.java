package com.bmsnc.adapter.out.persistence;

import com.bmsnc.adapter.out.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String movieName;
    @Enumerated(EnumType.STRING)
    private MovieGrade movieGrade;
    private LocalDateTime movieReleaseAt;
    private String movieImageUrl;
    private Long runningTime;
    private MovieGenre movieGenre;

}
