package com.bmsnc.adapter.out.persistence;

import com.bmsnc.adapter.out.BaseEntity;
import com.bmsnc.applicaion.domain.model.MovieModel;
import com.bmsnc.common.dto.MovieGenre;
import com.bmsnc.common.dto.MovieGrade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    public MovieModel toModel() {
        return MovieModel.builder()
                .movieId(movieId)
                .movieName(movieName)
                .movieGrade(movieGrade)
                .movieReleaseAt(movieReleaseAt)
                .movieImageUrl(movieImageUrl)
                .runningTime(runningTime)
                .movieGenre(movieGenre)
                .build();

    }

}
