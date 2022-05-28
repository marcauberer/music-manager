package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.application.exception.RegistrationFailedException;
import com.marc_auberer.musicmanager.application.exception.UserAlreadyExistsException;
import com.marc_auberer.musicmanager.application.exception.UserNotFoundException;
import com.marc_auberer.musicmanager.domain.user.User;

public interface UserService {

    User login(String username, String password) throws UserNotFoundException;

    User register(String username, String password) throws UserAlreadyExistsException, RegistrationFailedException;

}
