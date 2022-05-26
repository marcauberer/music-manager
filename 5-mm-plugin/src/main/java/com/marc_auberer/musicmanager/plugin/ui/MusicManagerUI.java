package com.marc_auberer.musicmanager.plugin.ui;

import com.marc_auberer.musicmanager.application.service.SongService;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.user.User;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class MusicManagerUI extends JFrame {

    private final SongService songService;

    public MusicManagerUI(User user) {
        // Initialize song service
        songService = new SongService(user);

        // Setup window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 900, 600);
        setTitle("Music Manager - Home");
        setResizable(false);
        setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();

        // Set root layout
        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(rootPanel);

        // Load songs
        List<Song> songs = songService.getAllSongsForUser();
        String[][] songData = songs.stream().map(song -> {
            String songTitle = song.getTitle();
            String songArtists = song.getArtists().stream()
                    .map(artist -> artist.getFirstName() + " "+ artist.getLastName())
                    .collect(Collectors.joining(", "));
            String songGenre = song.getGenre().getName();
            String songBpm = String.valueOf(song.getBpm());
            String songBarType = song.getBarType().getBeatCount() + "/" + song.getBarType().getBeatValue();
            return new String[]{songTitle, songArtists, songGenre, songBpm, songBarType};
        }).toArray(String[][]::new);

        // Song list
        String[] columnNames = {"Song Title", "Artist", "Genre", "Bpm", "Bar type"};
        JTable songList = new JTable(songData, columnNames);
        JScrollPane songScrollPane = new JScrollPane(songList);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.gridheight = 4;
        constraints.weightx = 4;
        rootPanel.add(songScrollPane, constraints);

        // Import button
        JButton buttonImport = new JButton("Import song");
        buttonImport.addActionListener(e -> {
            // Open file picker to select the song file
            final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Music (.mp3)", "mp3");
            final JFileChooser filePicker = new JFileChooser();
            filePicker.setDialogTitle("Music Manager - Import song");
            filePicker.setApproveButtonText("Import");
            filePicker.setFileFilter(extensionFilter);
            filePicker.setMultiSelectionEnabled(false);
            int result = filePicker.showOpenDialog(rootPanel);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = filePicker.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
        });
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        rootPanel.add(buttonImport, constraints);

        // Edit button
        JButton buttonEdit = new JButton("Edit item");
        buttonEdit.setEnabled(false);
        buttonEdit.addActionListener(e -> {

        });
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.weightx = 1;
        rootPanel.add(buttonEdit, constraints);

        // Delete button
        JButton buttonDelete = new JButton("Delete item");
        buttonDelete.setEnabled(false);
        buttonDelete.addActionListener(e -> {

        });
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.weightx = 1;
        rootPanel.add(buttonDelete, constraints);

        // Logout button
        JButton buttonLogin = new JButton("LogOut");
        buttonLogin.addActionListener(e -> {
            dispose();

        });
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.weightx = 1;
        rootPanel.add(buttonLogin, constraints);
    }
}
