package com.movie.storage.movie.repository;

import com.movie.domain.userAccount.UserAccount;
import com.movie.storage.userAccount.UserAccountRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserAccountRepositoryImplTest {

    @Autowired
    private UserAccountRepositoryImpl sut;

    @Test
    void givenToken_whenSearchingUserAccountByToken_thenReturnsUserAccount() {
        // given
        String token = "734488355d85";

        // when
        UserAccount userAccount = sut.getUserAccountByToken(token);

        // then
        assertThat(userAccount).isNotNull();
        assertThat(userAccount.getToken()).isEqualTo(token);
    }

}