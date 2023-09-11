package com.example.service;

import com.example.pojo.User;

public interface UserService {
    void add(User user);

    User selectByUsername(String username);

}
