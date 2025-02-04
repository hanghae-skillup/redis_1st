package com.example.infrastructure.db.persistence;

import com.example.application.port.out.MemberRepositoryPort;
import com.example.domain.model.entity.Member;
import com.example.infrastructure.db.MemberJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberJpaRepositoryAdapter implements MemberRepositoryPort {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Optional<Member> findById(Long id) {
        return memberJpaRepository.findById(id);
    }

}
