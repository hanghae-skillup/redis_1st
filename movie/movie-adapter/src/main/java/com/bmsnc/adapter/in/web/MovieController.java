package com.bmsnc.adapter.in.web;

import com.bmsnc.applicaion.domain.service.MovieUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieUseCaseService movieUseCaseService;


}
