package module.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import module.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}
