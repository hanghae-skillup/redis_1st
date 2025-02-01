package com.example.application.port.out;

import com.example.domain.model.entity.Member;
import java.util.Optional;

public interface MemberRepositoryPort {
    Optional<Member> findById(Long id);
}
