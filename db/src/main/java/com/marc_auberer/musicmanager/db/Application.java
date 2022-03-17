package com.marc_auberer.musicmanager.db;

import com.marc_auberer.musicmanager.db.persistence.*;
import com.marc_auberer.musicmanager.db.ui.LoginUI;

import javax.swing.*;
import java.awt.*;

public class Application {
    public static void main(String[] args) {
        // Initialize application
        Database.setupDatabase();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();

        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("APP ERROR: Could not set look and feel");
        }

        EventQueue.invokeLater(() -> {
            try {
                JFrame loginFrame = new LoginUI(userRepository);
                loginFrame.setVisible(true);
            } catch (Exception e) {
                System.out.println("APP ERROR: Could not show window");
            }
        });
    }
}