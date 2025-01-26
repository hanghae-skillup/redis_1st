package com.study.cinema.infra.jpa

import com.querydsl.jpa.DefaultQueryHandler
import com.querydsl.jpa.Hibernate5Templates
import com.querydsl.jpa.QueryHandler

class CustomerHibernate5Templates: Hibernate5Templates() {

    override fun getQueryHandler(): QueryHandler {
        return DefaultQueryHandler.DEFAULT
    }
}