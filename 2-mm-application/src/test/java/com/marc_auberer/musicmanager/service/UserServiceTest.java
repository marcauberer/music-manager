package com.marc_auberer.musicmanager.service;

import com.marc_auberer.musicmanager.application.exception.UserAlreadyExistsException;
import com.marc_auberer.musicmanager.application.exception.UserNotFoundException;
import com.marc_auberer.musicmanager.application.exception.WrongPasswordException;
import com.marc_auberer.musicmanager.application.service.UserService;
import com.marc_auberer.musicmanager.application.service.impl.UserServiceImpl;
import com.marc_auberer.musicmanager.domain.user.User;
import com.marc_auberer.musicmanager.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.marc_auberer.musicmanager.domain.AbstractRepository.AUTO_INC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    protected void prepareTests() {
        MockitoAnnotations.openMocks(this);

        // UserRepository findUserByUsername
        User mockUser0 = new User(0, "marc", "12345");
        User mockUser1 = new User(1, "testuser", "testpw");
        User mockUser2 = new User(2, "dhbw", "dhbwpass");
        when(userRepository.findUserByUsername("marc")).thenReturn(Optional.of(mockUser0));
        when(userRepository.findUserByUsername("testuser")).thenReturn(Optional.of(mockUser1));
        when(userRepository.findUserByUsername("dhbw")).thenReturn(Optional.of(mockUser2));

        // UserRepository findAllUsers
        List<User> mockUsers = List.of(
                mockUser0,
                mockUser1,
                mockUser2
        );
        when(userRepository.findAllUsers()).thenReturn(mockUsers);

        // UserRepository save()
        doNothing().when(userRepository).save(any());
    }

    @Test
    protected void loginSuccess() {
        // Test data
        UserService userService = new UserServiceImpl(userRepository);

        // Execute
        User actualResult = userService.login("marc", "12345");

        // Assert
        assertEquals(0, actualResult.getId());
    }

    @Test
    protected void loginUnknownUsername() {
        // Test data
        UserService userService = new UserServiceImpl(userRepository);

        // Execute
        String actualErrorMessage = assertThrowsExactly(UserNotFoundException.class, () -> {
            userService.login("typo", "12345");
        }).getMessage();

        // Assert
        assertEquals("The user typo was not found.", actualErrorMessage);
    }

    @Test
    protected void loginWrongPassword() {
        // Test data
        UserService userService = new UserServiceImpl(userRepository);

        // Execute
        String actualErrorMessage = assertThrowsExactly(WrongPasswordException.class, () -> {
            userService.login("dhbw", "dhbwpss");
        }).getMessage();

        // Assert
        assertEquals("The user dhbw was found, but you entered a wrong password.", actualErrorMessage);
    }

    @Test
    protected void registerSuccess() {
        // Test data
        UserService userService = new UserServiceImpl(userRepository);

        // Execute
        User actualUser = userService.register("new-user", "new-password");

        // Assert
        User expectedUser = new User(AUTO_INC, "new-user", "new-password");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    protected void registerUserAlreadyExists() {
        // Test data
        UserService userService = new UserServiceImpl(userRepository);

        // Execute
        String actualErrorMessage = assertThrowsExactly(UserAlreadyExistsException.class, () -> {
            userService.register("marc", "");
        }).getMessage();

        // Assert
        assertEquals("The user marc already exists.", actualErrorMessage);
    }
}
