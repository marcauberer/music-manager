package com.marc_auberer.musicmanager.plugin;

import javax.swing.*;
import java.awt.*;

public class Application {
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            EventQueue.invokeLater(MusicManager::new);
        } catch (Exception e) {
            System.err.println("MusicManager: Could not start application");
        }
    }
}
