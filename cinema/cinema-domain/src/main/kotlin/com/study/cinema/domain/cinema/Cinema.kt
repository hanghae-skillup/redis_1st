package com.study.cinema.domain.cinema

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Entity
@Table(name = "cinema")
class Cinema(
    name: String,
    address: String,
    contactNumber: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Comment("영화관 이름")
    var name: String = name
        protected set

    @Comment("영화관 주소")
    var address: String = address
        protected set

    @Comment("영화관 연락처")
    var contactNumber: String = contactNumber
        protected set
}