//package com.example.demo
//
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.boot.runApplication
//import org.springframework.context.annotation.Bean
//import org.springframework.data.jpa.repository.JpaRepository
//import org.springframework.stereotype.Service
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestParam
//import org.springframework.web.bind.annotation.RestController
//import org.springframework.data.redis.core.RedisTemplate
//import org.springframework.data.redis.core.ValueOperations
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.cache.annotation.Cacheable
//import jakarta.persistence.*
//import java.time.LocalDateTime
//import java.util.concurrent.TimeUnit
//import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType
//
//@SpringBootApplication
//class DemoApplication
//
//fun main(args: Array<String>) {
//	runApplication<DemoApplication>(*args)
//}
//
//@Entity
//@Table(name = "movies")
//data class Movie(
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	val id: Long = 0,
//
//	@Column(nullable = false, length = 255)
//	val title: String,
//
//	@Enumerated(EnumType.STRING)
//	val rating: Rating,
//
//	val releaseDate: LocalDateTime,
//
//	val thumbnailUrl: String,
//
//	val runningTime: Int,
//
//	val genre: String
//)
//
//@Entity
//@Table(name = "screenings")
//data class Screening(
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	val id: Long = 0,
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "movie_id", nullable = false)
//	val movie: Movie,
//
//	val cinemaName: String,
//
//	val startTime: LocalDateTime
//)
//
//enum class Rating {
//	GENERAL, PG13, R, NC17
//}
//
//interface MovieRepository : JpaRepository<Movie, Long> {
//	fun findByTitleContainingIgnoreCaseAndGenreContainingIgnoreCase(
//		title: String?, genre: String?
//	): List<Movie>
//}
//
//interface ScreeningRepository : JpaRepository<Screening, Long>
//
//@Service
//class MovieService(
//	private val movieRepository: MovieRepository,
//	private val screeningRepository: ScreeningRepository,
//	@Autowired private val redisTemplate: RedisTemplate<String, Any>
//) {
//	fun getNowShowingMovies(title: String?, genre: String?): List<MovieDto> {
//		val movies = movieRepository.findByTitleContainingIgnoreCaseAndGenreContainingIgnoreCase(
//			title = title ?: "",
//			genre = genre ?: ""
//		)
//		val screenings = screeningRepository.findAll().sortedBy { it.startTime }
//		return screenings.groupBy { it.movie }
//			.filterKeys { it in movies }
//			.map { (movie, screenings) ->
//				MovieDto(
//					title = movie.title,
//					rating = movie.rating.name,
//					releaseDate = movie.releaseDate,
//					thumbnailUrl = movie.thumbnailUrl,
//					runningTime = movie.runningTime,
//					genre = movie.genre,
//					screenings = screenings.map {
//						ScreeningDto(
//							cinemaName = it.cinemaName,
//							startTime = it.startTime
//						)
//					}
//				)
//			}
//			.sortedByDescending { it.releaseDate }
//	}
//
//	fun getNowShowingMoviesWithCaching(title: String?, genre: String?): List<MovieDto> {
//		val cacheKey = "movies::${title ?: ""}::${genre ?: ""}"
//		val ops: ValueOperations<String, Any> = redisTemplate.opsForValue()
//
//		@Suppress("UNCHECKED_CAST")
//		val cachedResult = ops.get(cacheKey) as? List<MovieDto>
//		if (cachedResult != null) {
//			return cachedResult
//		}
//
//		val movies = getNowShowingMovies(title, genre)
//		ops.set(cacheKey, movies, 10, TimeUnit.MINUTES)
//		return movies
//	}
//}
//
//@RestController
//class MovieController(private val movieService: MovieService) {
//
//	@GetMapping("/movies/now-showing")
//	fun getNowShowingMovies(
//		@RequestParam title: String?,
//		@RequestParam genre: String?
//	): List<MovieDto> {
//		return movieService.getNowShowingMovies(title, genre)
//	}
//
//	@GetMapping("/movies/now-showing/cached")
//	fun getNowShowingMoviesWithCaching(
//		@RequestParam title: String?,
//		@RequestParam genre: String?
//	): List<MovieDto> {
//		return movieService.getNowShowingMoviesWithCaching(title, genre)
//	}
//}
//
//data class MovieDto(
//	val title: String,
//	val rating: String,
//	val releaseDate: LocalDateTime,
//	val thumbnailUrl: String,
//	val runningTime: Int,
//	val genre: String,
//	val screenings: List<ScreeningDto>
//)
//
//data class ScreeningDto(
//	val cinemaName: String,
//	val startTime: LocalDateTime
//)
