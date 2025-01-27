package com.example.redis.cmmn

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseEntity(

    @CreatedDate
    @Column(name = "create_at")
    var createAt: LocalDateTime? = null,

    @Column(name = "create_by")
    var createBy: String? = null,

    @LastModifiedDate
    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null,

    @Column(name = "update_by")
    var updateBy: String? = null
) {
}