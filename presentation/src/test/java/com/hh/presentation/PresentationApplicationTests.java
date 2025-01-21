package com.hh.presentation;

import com.hh.application.movie.MovieService;
import com.hh.presentation.movie.MovieController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PresentationApplicationTests {

  @Autowired
  MovieService movieService;

  @Autowired
  MovieController movieController;



  @Test
  void contextLoads() {
    //System.out.println(movieService.getMovies().toArray());
    System.out.println(movieController.screenList().getResult().get(0).toString());
    System.out.println("test");
  }

  @Test
  void contextLoads2() {
    //System.out.println(movieService.getMovies().toArray());
    System.out.println(movieService.findMoviesWithGroupConcat());
    System.out.println("test");
  }
}
