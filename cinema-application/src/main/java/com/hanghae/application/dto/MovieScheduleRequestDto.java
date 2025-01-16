package com.hanghae.application.dto;

import com.hanghae.domain.model.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieScheduleRequestDto {
    private String title;
    private MovieGenre genre;
}
