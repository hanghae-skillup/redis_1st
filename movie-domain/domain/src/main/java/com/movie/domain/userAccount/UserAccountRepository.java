package com.movie.domain.userAccount;

public interface UserAccountRepository {

    UserAccount getUserAccountByToken(String token);

}
