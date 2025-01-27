package com.movie.domain.repository;

import com.movie.domain.entity.User;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    User findByEmail(String email);
} 