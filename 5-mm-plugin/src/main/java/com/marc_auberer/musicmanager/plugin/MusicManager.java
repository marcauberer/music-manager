package com.marc_auberer.musicmanager.plugin;

import com.marc_auberer.musicmanager.application.service.LoginObserver;
import com.marc_auberer.musicmanager.domain.user.User;
import com.marc_auberer.musicmanager.plugin.ui.LoginUI;
import com.marc_auberer.musicmanager.plugin.ui.MusicManagerUI;

import javax.swing.*;

public class MusicManager implements LoginObserver {

    MusicManager() {
        showLoginFrame();
    }

    @Override
    public void onLogin(User user) {
        showMainFrame(user);
    }

    @Override
    public void onLogout() {
        showLoginFrame();
    }

    private void showLoginFrame() {
        try {
            JFrame loginFrame = new LoginUI(this);
            loginFrame.setVisible(true);
        } catch (Exception e) {
            System.err.println("MusicManager: Could not open login window");
        }
    }

    private void showMainFrame(User user) {
        try {
            JFrame mainFrame = new MusicManagerUI(this, user);
            mainFrame.setVisible(true);
        } catch (Exception e) {
            System.err.println("MusicManager: Could not open main window");
        }
    }
}
