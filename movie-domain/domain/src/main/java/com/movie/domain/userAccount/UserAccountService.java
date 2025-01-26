package com.movie.domain.userAccount;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public UserAccount getUserAccountByToken(String token) {
        return userAccountRepository.getUserAccountByToken(token);
    }

}
