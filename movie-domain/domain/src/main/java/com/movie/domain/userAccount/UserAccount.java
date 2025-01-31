package com.movie.domain.userAccount;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccount {

    private Long id;
    private String name;
    private String token;

    public UserAccount(Long id, String name, String token) {
        this.id = id;
        this.name = name;
        this.token = token;
    }

    public static UserAccount of(Long id, String name, String token) {
        return new UserAccount(id, name, token);
    }

}
