package com.hh.domain.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FirmRating {
  ALL("전체 관람가"),
  AGE_12("12세 관람가"),
  AGE_15("15세 관람가"),
  RESTRICTED("청소년 관람불가"),
  LIMITED("제한상영가");

  private final String firmRateValue;


}
