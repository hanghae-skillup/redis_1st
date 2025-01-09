package com.example.presentation.user.model;

import com.example.domain.user.entity.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String name;

    private Integer age;

    public static User of(UserRequestDTO dto){
        return User.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .build();
    }

}
