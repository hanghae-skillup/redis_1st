package com.example.domain.user.service;

import com.example.domain.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    public void save(User user);

}
