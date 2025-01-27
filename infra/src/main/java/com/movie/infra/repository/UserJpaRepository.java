package com.movie.infra.repository;

import com.movie.domain.entity.User;
import com.movie.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
    @Override
    default User findById(Long id) {
        return findById(id).orElse(null);
    }

    @Override
    User findByEmail(String email);
} 