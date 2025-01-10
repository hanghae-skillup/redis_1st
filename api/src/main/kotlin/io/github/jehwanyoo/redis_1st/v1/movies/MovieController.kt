package io.github.jehwanyoo.redis_1st.v1.movies

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class MovieController {

    @GetMapping("/movies")
    fun getMovies() {

    }
}
