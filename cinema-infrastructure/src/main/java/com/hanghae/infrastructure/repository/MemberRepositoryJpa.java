package com.hanghae.infrastructure.repository;

import com.hanghae.infrastructure.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepositoryJpa extends JpaRepository<MemberEntity, Long> {
}
