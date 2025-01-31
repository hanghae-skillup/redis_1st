package com.hanghae.infrastructure.adapter;

import com.hanghae.application.port.out.MemberRepositoryPort;
import com.hanghae.domain.model.Member;
import com.hanghae.infrastructure.mapper.MemberMapper;
import com.hanghae.infrastructure.repository.MemberRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberRepositoryAdapter implements MemberRepositoryPort {
    private final MemberRepositoryJpa repository;

    @Override
    public Member findById(Long id) {
        return MemberMapper.toDomain(repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원정보를 찾을 수 없습니다.")));
    }
}
