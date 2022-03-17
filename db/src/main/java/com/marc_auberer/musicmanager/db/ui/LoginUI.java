package com.marc_auberer.musicmanager.db.ui;

import com.marc_auberer.musicmanager.db.persistence.UserRepositoryImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {

    // Repositories
    private final UserRepositoryImpl userRepository;

    // Components

    public LoginUI(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;

        // Setup window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 350, 140);
        setTitle("Music Manager - Login");
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JPanel rootPanel = new JPanel(new GridLayout(3, 2));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(rootPanel);

        // Username text field
        JLabel labelUsername = new JLabel("Username:");
        rootPanel.add(labelUsername);
        JTextField textFieldUsername = new JTextField();
        rootPanel.add(textFieldUsername);

        // Password text field
        JLabel labelPassword = new JLabel("Password:");
        rootPanel.add(labelPassword);
        JPasswordField textFieldPassword = new JPasswordField();
        rootPanel.add(textFieldPassword);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {

        });
        rootPanel.add(registerButton);

        // Login button
        JButton buttonLogin = new JButton("LogIn");
        buttonLogin.addActionListener(e -> {

        });
        rootPanel.add(buttonLogin);

        // Set focus to username field
        textFieldUsername.requestFocus();
    }
}
