package com.movie.app.movice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/movies")
    public ResponseEntity<String> getMovies() {
        return ResponseEntity.ok("Movie List");
    }
}
