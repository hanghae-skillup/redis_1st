package com.movie.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchCondition {
    @Size(max = 100, message = "영화 제목은 100자를 초과할 수 없습니다")
    private String title;

    @Size(max = 50, message = "장르는 50자를 초과할 수 없습니다")
    private String genre;
} 