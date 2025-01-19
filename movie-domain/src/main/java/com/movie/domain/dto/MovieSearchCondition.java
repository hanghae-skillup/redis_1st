package com.movie.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchCondition {
    @Size(max = 255, message = "영화 제목은 255자를 초과할 수 없습니다.")
    private String title;
    private String genre;
} 