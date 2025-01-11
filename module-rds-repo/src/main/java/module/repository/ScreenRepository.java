package module.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import module.entity.Screen;

public interface ScreenRepository extends JpaRepository<Screen,Long> {
}
