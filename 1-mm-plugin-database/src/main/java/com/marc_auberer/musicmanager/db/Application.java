package com.marc_auberer.musicmanager.db;

import com.marc_auberer.musicmanager.application.MusicManager;
import com.marc_auberer.musicmanager.application.service.UserService;
import com.marc_auberer.musicmanager.db.persistence.*;
import com.marc_auberer.musicmanager.db.ui.LoginUI;
import com.marc_auberer.musicmanager.domain.genre.Genre;

import javax.swing.*;
import java.awt.*;

public class Application {
    public static void main(String[] args) {
        // Create repositories
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        SongRepositoryImpl songRepository = new SongRepositoryImpl();
        ArtistRepositoryImpl artistRepository = new ArtistRepositoryImpl();
        GenreRepositoryImpl genreRepository = new GenreRepositoryImpl();
        BarTypeRepositoryImpl barTypeRepository = new BarTypeRepositoryImpl();

        // Create Music Manager instance
        MusicManager musicManager = new MusicManager(
                userRepository,
                songRepository,
                artistRepository,
                genreRepository,
                barTypeRepository
        );

        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("APP ERROR: Could not set look and feel");
        }

        EventQueue.invokeLater(() -> {
            try {
                JFrame loginFrame = new LoginUI(musicManager);
                loginFrame.setVisible(true);
            } catch (Exception e) {
                System.out.println("APP ERROR: Could not show window");
            }
        });
    }
}