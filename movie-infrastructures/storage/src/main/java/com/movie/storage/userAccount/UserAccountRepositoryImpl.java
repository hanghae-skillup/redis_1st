package com.movie.storage.userAccount;

import com.movie.domain.userAccount.UserAccount;
import com.movie.domain.userAccount.UserAccountRepository;
import com.movie.storage.mapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAccountRepositoryImpl implements UserAccountRepository {

    private final UserAccountJpaRepository userAccountJpaRepository;

    @Override
    public UserAccount getUserAccountByToken(String token) {
        UserAccountEntity userAccount = userAccountJpaRepository.findByToken(token);
        return ModelMapper.from(userAccount);
    }

}
