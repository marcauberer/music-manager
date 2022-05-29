package com.marc_auberer.musicmanager.plugin.ui;

import com.marc_auberer.musicmanager.application.exception.UserAlreadyExistsException;
import com.marc_auberer.musicmanager.application.exception.UserNotFoundException;
import com.marc_auberer.musicmanager.application.exception.WrongPasswordException;
import com.marc_auberer.musicmanager.application.observer.LoginObserver;
import com.marc_auberer.musicmanager.application.service.impl.UserServiceImpl;
import com.marc_auberer.musicmanager.domain.user.User;
import com.marc_auberer.musicmanager.domain.user.UserRepository;
import com.marc_auberer.musicmanager.plugin.repository.UserRepositoryImpl;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    // UI Components
    private JLabel errorLabel;
    private final JTextField textFieldUsername = new JTextField();
    private final JPasswordField textFieldPassword = new JPasswordField();

    // Members
    private final UserServiceImpl userService;
    private final LoginObserver loginObserver;

    public LoginUI(LoginObserver loginObserver) {
        this.loginObserver = loginObserver;

        // Create user service
        UserRepository userRepository = new UserRepositoryImpl();
        this.userService = new UserServiceImpl(userRepository);

        // Setup window
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(0, 0, 350, 150);
        setTitle("Music Manager - Login");
        setResizable(false);
        setLocationRelativeTo(null);

        // Set root layout
        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(rootPanel);

        // Info/error label
        JLabel labelInfo = new JLabel("Please enter your login credentials below");
        UIHelper.placeUIComp(rootPanel, labelInfo, 0, 0, 2, 1, 2);

        // Username text field
        JLabel labelUsername = new JLabel("Username:");
        UIHelper.placeUIComp(rootPanel, labelUsername, 0, 1, 1, 1, 1);
        UIHelper.placeUIComp(rootPanel, textFieldUsername, 1, 1, 1, 1, 1);

        // Password text field
        JLabel labelPassword = new JLabel("Password:");
        UIHelper.placeUIComp(rootPanel, labelPassword, 0, 2, 1, 1, 1);
        textFieldPassword.addActionListener(e -> attemptToLogin());
        UIHelper.placeUIComp(rootPanel, textFieldPassword, 1, 2, 1, 1, 1);

        // Error label
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        UIHelper.placeUIComp(rootPanel, errorLabel, 0, 3, 2, 1, 2);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> register());
        UIHelper.placeUIComp(rootPanel, registerButton, 0, 4, 1, 1, 1);

        // Login button
        JButton buttonLogin = new JButton("LogIn");
        buttonLogin.addActionListener(e -> attemptToLogin());
        UIHelper.placeUIComp(rootPanel, buttonLogin, 1, 4, 1, 1, 1);

        // Set focus to username field
        textFieldUsername.requestFocus();
    }

    private void attemptToLogin() {
        errorLabel.setText(null);
        String username = textFieldUsername.getText().trim();
        String password = new String(textFieldPassword.getPassword());

        // Try to log in
        try {
            User user = userService.login(username, password);
            dispose();
            loginObserver.onLogin(user);
        } catch (UserNotFoundException | WrongPasswordException ex) {
            errorLabel.setText(ex.getMessage());
        }
    }

    private void register() {
        errorLabel.setText(null);
        String username = textFieldUsername.getText().trim();
        String password = new String(textFieldPassword.getPassword());

        // Try to register
        try {
            User user = userService.register(username, password);
            dispose();
            loginObserver.onLogin(user);
        } catch (UserAlreadyExistsException ex) {
            errorLabel.setText(ex.getMessage());
        }
    }
}
