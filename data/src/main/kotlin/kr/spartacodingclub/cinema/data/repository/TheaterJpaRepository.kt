package kr.spartacodingclub.cinema.data.repository

import kr.spartacodingclub.cinema.data.entity.TheaterEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TheaterJpaRepository : JpaRepository<TheaterEntity, Long>
