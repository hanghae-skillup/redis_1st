package com.movie.infra.repository;

import com.movie.domain.entity.User;
import com.movie.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
    @Override
    User findByEmail(String email);
} 