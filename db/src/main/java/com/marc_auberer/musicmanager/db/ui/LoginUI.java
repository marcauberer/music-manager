package com.marc_auberer.musicmanager.db.ui;

import com.marc_auberer.musicmanager.application.MusicManager;
import com.marc_auberer.musicmanager.application.exception.UserAlreadyExistsException;
import com.marc_auberer.musicmanager.application.exception.UserNotFoundException;
import com.marc_auberer.musicmanager.domain.user.User;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    // Constants
    private final String INFO_TEXT = "Please enter your login credentials below";

    // Components
    private final JLabel labelInfo = new JLabel(INFO_TEXT);
    private final JTextField textFieldUsername = new JTextField();
    private final JPasswordField textFieldPassword = new JPasswordField();

    // Members
    private final MusicManager musicManager;

    public LoginUI(MusicManager musicManager) {
        this.musicManager = musicManager;

        // Setup window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 350, 140);
        setTitle("Music Manager - Login");
        setResizable(false);
        setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();

        // Set root layout
        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(rootPanel);

        // Info/error label
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 2;
        rootPanel.add(labelInfo, constraints);

        // Username text field
        JLabel labelUsername = new JLabel("Username:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        rootPanel.add(labelUsername, constraints);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1;
        rootPanel.add(textFieldUsername, constraints);

        // Password text field
        JLabel labelPassword = new JLabel("Password:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 1;
        rootPanel.add(labelPassword, constraints);
        textFieldPassword.addActionListener(e -> attemptToLogin());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 1;
        rootPanel.add(textFieldPassword, constraints);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {

        });
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weightx = 1;
        rootPanel.add(registerButton, constraints);

        // Login button
        JButton buttonLogin = new JButton("LogIn");
        buttonLogin.addActionListener(e -> attemptToLogin());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.weightx = 1;
        rootPanel.add(buttonLogin, constraints);

        // Set focus to username field
        textFieldUsername.requestFocus();
    }

    private void attemptToLogin() {
        labelInfo.setText(INFO_TEXT);
        String username = textFieldUsername.getText().trim();
        String password = new String(textFieldPassword.getPassword());

        // Try to log in
        try {
            User user = musicManager.login(username, password);
            assert user != null;
            dispose();
            // Show main window
            MusicManagerUI musicManagerUI = new MusicManagerUI(musicManager, user);
            musicManagerUI.setVisible(true);
        } catch (UserNotFoundException ex) {
            labelInfo.setText(ex.getMessage());
        }
    }

    private void register() {
        labelInfo.setText(INFO_TEXT);
        String username = textFieldUsername.getText().trim();
        String password = new String(textFieldPassword.getPassword());

        // Try to register
        try {
            musicManager.register(username, password);
        } catch (UserAlreadyExistsException ex) {
            labelInfo.setText(ex.getMessage());
        }
    }
}
