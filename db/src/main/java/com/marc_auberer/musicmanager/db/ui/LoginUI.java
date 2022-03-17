package com.marc_auberer.musicmanager.db.ui;

import com.marc_auberer.musicmanager.application.MusicManager;
import com.marc_auberer.musicmanager.application.exception.UserAlreadyExistsException;
import com.marc_auberer.musicmanager.application.exception.UserNotFoundException;
import com.marc_auberer.musicmanager.application.service.UserService;
import com.marc_auberer.musicmanager.db.persistence.UserRepositoryImpl;
import com.marc_auberer.musicmanager.domain.user.User;
import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginUI extends JFrame {

    private final String INFO_TEXT = "Please enter your login credentials below";

    public LoginUI(MusicManager musicManager) {
        // Setup window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 350, 140);
        setTitle("Music Manager - Login");
        setResizable(false);
        setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(rootPanel);

        // Info/error label
        JLabel labelInfo = new JLabel(INFO_TEXT);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        rootPanel.add(labelInfo, constraints);

        // Username text field
        JLabel labelUsername = new JLabel("Username:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        rootPanel.add(labelUsername, constraints);
        JTextField textFieldUsername = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        rootPanel.add(textFieldUsername, constraints);

        // Password text field
        JLabel labelPassword = new JLabel("Password:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        rootPanel.add(labelPassword, constraints);
        JPasswordField textFieldPassword = new JPasswordField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        rootPanel.add(textFieldPassword, constraints);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            labelInfo.setText(INFO_TEXT);
            String username = textFieldUsername.getText().trim();
            String password = new String(textFieldPassword.getPassword());

            // Try to register
            try {
                musicManager.register(username, password);
            } catch (UserAlreadyExistsException ex) {
                labelInfo.setText(ex.getMessage());
            }
        });
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        rootPanel.add(registerButton, constraints);

        // Login button
        JButton buttonLogin = new JButton("LogIn");
        buttonLogin.addActionListener(e -> {
            labelInfo.setText(INFO_TEXT);
            String username = textFieldUsername.getText().trim();
            String password = new String(textFieldPassword.getPassword());

            // Try to log in
            try {
                User user = musicManager.login(username, password);
                if (user != null) {
                    dispose();
                }
            } catch (UserNotFoundException ex) {
                labelInfo.setText(ex.getMessage());
            }
        });
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        rootPanel.add(buttonLogin, constraints);

        // Set focus to username field
        textFieldUsername.requestFocus();
    }
}
