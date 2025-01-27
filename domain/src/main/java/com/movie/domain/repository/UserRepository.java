package com.movie.domain.repository;

import com.movie.domain.entity.User;

public interface UserRepository {
    User save(User user);
    User findById(Long id);
    User findByEmail(String email);
} 