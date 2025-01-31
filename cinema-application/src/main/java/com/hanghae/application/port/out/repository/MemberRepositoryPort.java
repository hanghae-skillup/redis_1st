package com.hanghae.application.port.out.repository;

import com.hanghae.domain.model.Member;

public interface MemberRepositoryPort {
    Member findById(Long id);
}
