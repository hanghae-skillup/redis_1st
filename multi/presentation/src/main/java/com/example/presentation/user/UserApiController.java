package com.example.presentation.user;

import com.example.domain.user.service.UserService;
import com.example.presentation.user.model.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/save")
    public void save(@RequestBody UserRequestDTO user) {
        userService.save(UserRequestDTO.of(user));
    }
}
