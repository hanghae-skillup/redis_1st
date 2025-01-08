package com.example.hanghaecinema.service;

import com.example.hanghaecinema.controller.dto.MovieResponseDto;

import java.util.List;

public interface MovieService {
    List<MovieResponseDto> getNowShowingMovies();
}
