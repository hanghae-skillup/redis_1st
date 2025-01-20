package io.github.jehwanyoo.redis_1st.seeder

import io.github.jehwanyoo.redis_1st.entity.MovieEntity
import io.github.jehwanyoo.redis_1st.entity.ScreenEntity
import io.github.jehwanyoo.redis_1st.entity.ShowTimeEntity
import org.springframework.boot.CommandLineRunner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

@Component
class DataSeeder(
    private val movieRepository: MovieRepository,
    private val screenRepository: ScreenRepository,
    private val showTimeRepository: ShowTimeRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (movieRepository.count() > 0 || screenRepository.count() > 0 || showTimeRepository.count() > 0) {
            println("데이터가 이미 존재합니다. 시딩을 생략합니다.")
            return
        }

        // 영화 이름 리스트
        val movieNames = listOf(
            "인터스텔라", "어벤져스: 엔드게임", "인셉션", "다크 나이트",
            "소울", "라라랜드", "반지의 제왕", "토이스토리 4",
            "보헤미안 랩소디", "코코"
        )

        // 상영관 이름 리스트
        val screenNames = (1..10).map { "상영관 $it" }

        // 상영관 생성
        val screens = screenNames.map {
            ScreenEntity(null, it, Random.nextInt(5, 10), Random.nextInt(5, 10))
        }
        screenRepository.saveAll(screens)

        // 영화 생성
        val movies = movieNames.map {
            MovieEntity(
                null,
                it,
                LocalDate.now().minusDays(Random.nextLong(1, 1000)), // 랜덤 개봉일
                "https://example.com/${it.replace(" ", "_").lowercase()}.jpg",
                Random.nextInt(90, 180), // 90~180분 사이의 러닝타임
                listOf("드라마", "액션", "판타지", "코미디").random(),
                listOf("12세 관람가", "15세 관람가").random(),
                emptyList()
            )
        }
        movieRepository.saveAll(movies)

        // 상영 시간표 생성
        val showTimes = mutableListOf<ShowTimeEntity>()
        repeat(500) {
            val movie = movies.random()
            val screen = screens.random()
            val startTime = LocalDateTime.now().plusDays(Random.nextLong(1, 30))
                .withHour(Random.nextInt(9, 23)) // 09:00~23:00 사이 랜덤 시간
                .withMinute(listOf(0, 15, 30, 45).random())

            showTimes.add(
                ShowTimeEntity(
                    null,
                    startTime,
                    movie,
                    screen
                )
            )
        }
        showTimeRepository.saveAll(showTimes)

        println("시딩 완료: 영화 ${movies.size}개, 상영관 ${screens.size}개, 상영 시간표 ${showTimes.size}개")
    }
}

interface MovieRepository : JpaRepository<MovieEntity, UUID>

interface ScreenRepository : JpaRepository<ScreenEntity, UUID>

interface ShowTimeRepository : JpaRepository<ShowTimeEntity, UUID>