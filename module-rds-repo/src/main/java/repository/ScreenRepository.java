package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen,Long> {
}
