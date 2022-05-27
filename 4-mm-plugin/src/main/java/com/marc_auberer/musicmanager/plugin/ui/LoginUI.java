package com.marc_auberer.musicmanager.plugin.ui;

import com.marc_auberer.musicmanager.application.exception.UserAlreadyExistsException;
import com.marc_auberer.musicmanager.application.exception.UserNotFoundException;
import com.marc_auberer.musicmanager.application.exception.WrongPasswordException;
import com.marc_auberer.musicmanager.application.observer.LoginObserver;
import com.marc_auberer.musicmanager.application.service.UserService;
import com.marc_auberer.musicmanager.domain.user.User;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    // UI Components
    private JLabel errorLabel;
    private final JTextField textFieldUsername = new JTextField();
    private final JPasswordField textFieldPassword = new JPasswordField();

    // Members
    private final UserService userService;
    private final LoginObserver loginObserver;

    public LoginUI(LoginObserver loginObserver) {
        this.loginObserver = loginObserver;

        // Create user service
        this.userService = new UserService();

        // Setup window
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 350, 150);
        setTitle("Music Manager - Login");
        setResizable(false);
        setLocationRelativeTo(null);

        // Set root layout
        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(rootPanel);

        // Prepare constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 2;

        // Info/error label
        JLabel labelInfo = new JLabel("Please enter your login credentials below");
        rootPanel.add(labelInfo, constraints);

        // Username text field
        JLabel labelUsername = new JLabel("Username:");
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        rootPanel.add(labelUsername, constraints);
        constraints.gridx = 1;
        rootPanel.add(textFieldUsername, constraints);

        // Password text field
        JLabel labelPassword = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        rootPanel.add(labelPassword, constraints);
        textFieldPassword.addActionListener(e -> attemptToLogin());
        constraints.gridx = 1;
        rootPanel.add(textFieldPassword, constraints);

        // Error label
        errorLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.weightx = 2;
        rootPanel.add(errorLabel, constraints);
        errorLabel.setForeground(Color.RED);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> register());
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        rootPanel.add(registerButton, constraints);

        // Login button
        JButton buttonLogin = new JButton("LogIn");
        buttonLogin.addActionListener(e -> attemptToLogin());
        constraints.gridx = 1;
        rootPanel.add(buttonLogin, constraints);

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
