package com.redis.utils

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

class ExceptionUtils

fun fail() : Nothing {
    throw IllegalArgumentException()
}

fun <T, ID> CrudRepository<T, ID>.findByIdThrow(id: ID) : T {
    return this.findByIdOrNull(id) ?: fail()
}