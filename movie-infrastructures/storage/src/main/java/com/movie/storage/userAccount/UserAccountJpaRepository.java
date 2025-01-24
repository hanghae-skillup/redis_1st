package com.movie.storage.userAccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountJpaRepository extends JpaRepository<UserAccountEntity, Long> {

    UserAccountEntity findByToken(String token);

}
