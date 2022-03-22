package com.marc_auberer.musicmanager.db.ui;

import com.marc_auberer.musicmanager.domain.user.User;

import javax.swing.*;
import java.awt.*;

public class ImportDialog extends JFrame {

    public ImportDialog(User user) {
        // Setup window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 350, 500);
        setTitle("Music Manager - Import song");
        setResizable(false);
        setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();

        // Set root layout
        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(rootPanel);


    }
}
