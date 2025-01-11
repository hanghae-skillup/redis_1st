package kr.spartacodingclub.cinema.data.mapper

import kr.spartacodingclub.cinema.core.domain.Screening
import kr.spartacodingclub.cinema.data.entity.ScreeningEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface ScreeningMapper {
    companion object {
        val INSTANCE: ScreeningMapper = Mappers.getMapper(ScreeningMapper::class.java)
    }

    fun toScreening(entity: ScreeningEntity): Screening
    fun toScreeningEntity(domain: Screening): ScreeningEntity
}
