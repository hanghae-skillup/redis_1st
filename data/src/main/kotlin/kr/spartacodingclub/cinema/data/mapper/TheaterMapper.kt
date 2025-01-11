package kr.spartacodingclub.cinema.data.mapper

import kr.spartacodingclub.cinema.core.domain.Theater
import kr.spartacodingclub.cinema.data.entity.TheaterEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface TheaterMapper {
    companion object {
        val INSTANCE: TheaterMapper = Mappers.getMapper(TheaterMapper::class.java)
    }

    fun toTheater(entity: TheaterEntity): Theater
    fun toTheaterEntity(domain: Theater): TheaterEntity
}
