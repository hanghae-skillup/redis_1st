package com.hh.infrastructure.movie;

import com.hh.domain.movie.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
