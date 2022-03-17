package com.marc_auberer.musicmanager.db.ui;

import com.marc_auberer.musicmanager.db.persistence.UserRepositoryImpl;

import javax.swing.*;

public class LoginUI extends JFrame {

    private final UserRepositoryImpl userRepository;

    public LoginUI(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;

        // Setup window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 500, 400);
        setTitle("Music Manager - Login");
        setResizable(false);
        setLocationRelativeTo(null);

    }
}
