package com.marc_auberer.musicmanager.domain.user;

import java.util.List;

public interface UserRepository {

    List<User> findAllUsers();

    User findUserByUsername(String username);

    User save(User user);
}