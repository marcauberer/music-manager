package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.domain.user.User;
import com.marc_auberer.musicmanager.domain.user.UserRepository;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;
    private final User user;

    public UserService(UserRepository userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }

    public Optional<User> getUser() {
        return userRepository.findUserByUsername(user.getUsername());
    }
}
