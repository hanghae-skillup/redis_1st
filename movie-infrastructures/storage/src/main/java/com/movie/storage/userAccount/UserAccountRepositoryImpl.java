package com.movie.storage.userAccount;

import com.movie.domain.exception.ApplicationException;
import com.movie.domain.exception.ErrorCode;
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
        UserAccountEntity userAccount = userAccountJpaRepository.findByToken(token).orElseThrow(
                () -> new ApplicationException(ErrorCode.CONTENT_NOT_FOUND, "user not found by token - %s".formatted(token))
        );
        return ModelMapper.UserAccountMapper.from(userAccount);
    }

}
