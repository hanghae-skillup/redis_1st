package com.redis.utils

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(

    @CreatedDate
    @Column(name = "create_at", updatable = false)
    val createAt: LocalDateTime? = null,


    // @CreatedBy 을 통해 구현하려했으나, Spring Security까지 구현해야하므로 우선 보류
    @Column(name = "creator")
    val creator: String? = null,

    @LastModifiedDate
    @Column(name = "update_at")
    val updateAt: LocalDateTime? = null,

    // @LastModifiedBy 을 통해 구현하려했으나, Spring Security까지 구현해야하므로 우선 보류
    @Column(name = "updater")
    val updater: String? = null
) {


}