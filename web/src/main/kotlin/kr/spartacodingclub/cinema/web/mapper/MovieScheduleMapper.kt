package kr.spartacodingclub.cinema.web.mapper

import kr.spartacodingclub.cinema.core.usecase.MovieSchedule
import kr.spartacodingclub.cinema.core.usecase.ScreeningInfo
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import kr.spartacodingclub.cinema.web.controller.MovieScheduleResponse
import kr.spartacodingclub.cinema.web.controller.ScreeningResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Mapper
interface MovieScheduleMapper {
    companion object {
        @JvmStatic
        val INSTANCE: MovieScheduleMapper = Mappers.getMapper(MovieScheduleMapper::class.java)
    }

    @Mapping(target = "title", source = "movie.title")
    @Mapping(target = "rating", source = "movie.rating")
    @Mapping(target = "releaseDate", source = "movie.releaseDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "thumbnailUrl", source = "movie.thumbnailUrl")
    @Mapping(target = "runningTime", source = "movie.runningTime")
    @Mapping(target = "genre", source = "movie.genre")
    @Mapping(target = "screenings", source = "screenings")
    fun toMovieScheduleResponse(movieSchedule: MovieSchedule): MovieScheduleResponse

    @Mapping(target = "theaterName", source = "theater.name")
    @Mapping(target = "startTime", dateFormat = "yyyy-MM-dd HH:mm")
    fun toScreeningResponse(screeningInfo: ScreeningInfo): ScreeningResponse

    // 날짜 포맷 변환을 위한 default 메서드
    fun mapLocalDateTime(dateTime: LocalDateTime): String {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    }
}
