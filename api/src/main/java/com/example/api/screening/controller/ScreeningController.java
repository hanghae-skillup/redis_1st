package com.example.api.screening.controller;


import com.example.api.screening.dto.ScreeningResponseDto;
import com.example.api.screening.mapper.ScreeningDtoMapper;
import com.example.application.screening.service.ScreeningService;
import com.example.common.util.dto.DataResponse;
import com.example.domain.movie.entity.Movie;
import com.example.domain.screening.dto.ProjectionScreeningResponseDto;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;
    @GetMapping("/screenings/search")
    public ResponseEntity<DataResponse<List<ScreeningResponseDto>>> getScreenings(


            @RequestParam(required = false)
            @Size(max = 100, message = "Title length must not exceed 100 characters")
            String title,
            @RequestParam(required = false)
            Movie.Genre genre) {


        List<ProjectionScreeningResponseDto> screeningList = screeningService.getScreengings(title, genre);

        List<ScreeningResponseDto> screeningData = ScreeningDtoMapper.toScreeningResponseDto(screeningList);

        DataResponse<List<ScreeningResponseDto>> response = DataResponse.response(true, screeningData);

        return ResponseEntity.ok(response);
    }
}
