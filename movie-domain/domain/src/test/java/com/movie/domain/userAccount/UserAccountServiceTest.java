package com.movie.domain.userAccount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @InjectMocks
    private UserAccountService sut;

    @Mock
    private UserAccountRepository userAccountRepository;

    @DisplayName("토큰을 이용하여 사용자를 조회힌다.")
    @Test
    void givenToken_whenSearchingUserAccountByToken_thenReturnsUserAccount() {
        // given
        String token = "734488355d85";

        UserAccount userAccount = UserAccount.of(1L, "name", token);
        given(userAccountRepository.getUserAccountByToken(token)).willReturn(userAccount);

        // when
        UserAccount findUserAccount = sut.getUserAccountByToken(token);

        // then
        assertThat(findUserAccount.getId()).isEqualTo(userAccount.getId());
    }

}