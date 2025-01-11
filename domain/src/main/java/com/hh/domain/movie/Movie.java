package com.hh.domain.movie;

import com.hh.domain.common.AuditingFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@Entity
public class Movie extends AuditingFields {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ToString.Exclude
  @OneToMany()
  private final List<Screen> movieScreen = new ArrayList<>();

  @Column(nullable = false)
  private String title;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private FirmRating firmRating;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Genre genre;

  @Column(nullable = false)
  private Date releasedDate;

  @Column(nullable = false)
  private String thumbnail;

  @Column(nullable = false)
  private int runningTime;




}
