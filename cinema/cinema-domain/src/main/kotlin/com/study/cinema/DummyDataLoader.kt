package com.study.cinema

import com.study.cinema.domain.cinema.Cinema
import com.study.cinema.domain.movie.Genre
import com.study.cinema.domain.movie.Movie
import com.study.cinema.domain.movie.MovieInfo
import com.study.cinema.domain.movie.MovieRating
import com.study.cinema.domain.schedule.MovieSchedule
import com.study.cinema.domain.theater.Theater
import com.study.cinema.infra.jpa.movie.CinemaRepository
import com.study.cinema.infra.jpa.movie.MovieRepository
import com.study.cinema.infra.jpa.movie.MovieScheduleRepository
import com.study.cinema.infra.jpa.movie.TheaterRepository
import net.datafaker.Faker
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class DummyDataLoader(
    private val movieRepository: MovieRepository,
    private val theaterRepository: TheaterRepository,
    private val movieScheduleRepository: MovieScheduleRepository,
    private val cinemaRepository: CinemaRepository,
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val faker = Faker()
        val cinema = cinemaRepository.save(Cinema(
            name = "항해호",
            address = faker.address().fullAddress(),
            contactNumber = faker.phoneNumber().phoneNumber(),
        ))

        val movies = (1..1000).map {
            Movie(
                title = faker.movie().name(),
                genre = faker.options().option(Genre::class.java),
                movieRating = faker.options().option(MovieRating::class.java),
                releaseDate = faker.timeAndDate().birthday(),
                runningTimeMinutes = faker.random().nextInt(90, 240),
                posterUrl = "${faker.internet().url()}/images/movie_${it}.jpg"
            )
        }
        movieRepository.saveAll(movies)

        // 3. Theater 생성
        val theaters = (1..20).map {
            Theater(
                title = "상영관 $it",
                cinema = cinema
            )
        }
        theaterRepository.saveAll(theaters)

        val movieSchedules = (1..1000).map {
            val startAt = ZonedDateTime.now().plusDays(faker.random().nextLong(1, 20))

            MovieSchedule(
                movie = movies[it - 1],
                theater = theaters[it % 20],
                startAt = startAt,
                endAt = startAt.plusMinutes(movies[it - 1].runningTimeMinutes.toLong())
            )
        }
        movieScheduleRepository.saveAll(movieSchedules)
    }
}