package com.hh.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {
  ACTION("Action"),
  DRAMA("Drama"),
  COMEDY("Comedy"),
  THRILLER("Thriller"),
  HORROR("Horror"),
  ROMANCE("Romance"),
  SCI_FI("Science Fiction"),
  FANTASY("Fantasy"),
  ANIMATION("Animation");

  private final String displayName;
}
