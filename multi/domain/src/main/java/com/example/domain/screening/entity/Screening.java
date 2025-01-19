package com.example.domain.screening;


import com.example.domain.common.BaseEntity;
import com.example.domain.movies.entity.Movie;
import com.example.domain.theater.Theater;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Screenings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Screening extends BaseEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "screening_id")
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "movie_id")
   private Movie movie;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "theater_id")
   private Theater theater;

   @Column(name = "start_time")
   private LocalDateTime startTime;

   @Column(name = "end_time")
   private LocalDateTime endTime;

   @Column(name = "screening_date")
   private LocalDateTime screeningDate;

   public void setMovie(Movie movie) {
      this.movie = movie;
   }

   public void setTheater(Theater theater) {
      this.theater = theater;
   }

   public boolean checkReleaseDate(LocalDateTime releaseDate){
     return this.getScreeningDate().isAfter(releaseDate);

   }

}
