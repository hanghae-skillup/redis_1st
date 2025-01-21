package com.hh.domain.movie;

import com.hh.domain.common.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Movie extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String title;

  @Enumerated(EnumType.STRING)
  private FirmRating firmRating;

  @Enumerated(EnumType.STRING)
  private Genre genre;

  private LocalDateTime releasedDate;

  private String thumbnail;

  private int runningTime;




}
