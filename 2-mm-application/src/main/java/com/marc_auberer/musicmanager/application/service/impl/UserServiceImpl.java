package com.marc_auberer.musicmanager.application.service.impl;

import com.marc_auberer.musicmanager.application.exception.RegistrationFailedException;
import com.marc_auberer.musicmanager.application.exception.UserAlreadyExistsException;
import com.marc_auberer.musicmanager.application.exception.UserNotFoundException;
import com.marc_auberer.musicmanager.application.exception.WrongPasswordException;
import com.marc_auberer.musicmanager.application.service.UserService;
import com.marc_auberer.musicmanager.domain.user.User;
import com.marc_auberer.musicmanager.domain.user.UserRepository;

import java.util.Optional;

import static com.marc_auberer.musicmanager.domain.AbstractRepository.AUTO_INC;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        // Check if the user was found
        User user = optionalUser.orElseThrow(() ->
                new UserNotFoundException(String.format("The user %s was not found.", username))
        );
        // Check if the password is correct
        if (!password.equals(user.getPassword())) {
            String errorMessage = String.format("The user %s was found, but you entered a wrong password.", username);
            throw new WrongPasswordException(errorMessage);
        }
        return user;
    }

    @Override
    public User register(String username, String password) throws UserAlreadyExistsException, RegistrationFailedException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        // Check if the user was found
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException(String.format("The user %s already exists.", username));
        }
        // Create new user
        User user = new User(AUTO_INC, username, password);
        userRepository.save(user);
        // We need to load the user again to get the persisted user id
        optionalUser = userRepository.findUserByUsername(username);
        return optionalUser.orElse(user);
    }
}
