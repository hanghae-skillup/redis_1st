package com.study.cinema.domain.theater

import com.study.cinema.domain.cinema.Cinema
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
@Table
class Theater(
    title: String,
    seatLayout: String,
    cinema: Cinema,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Comment("상영관 이름")
    var title: String = title
        protected set

    @Comment("좌석 배치 정보")
    var seatLayout: String = seatLayout
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    val cinema: Cinema = cinema
}