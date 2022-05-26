package com.marc_auberer.musicmanager.plugin.ui;

import com.marc_auberer.musicmanager.application.observer.LoginObserver;
import com.marc_auberer.musicmanager.application.observer.SongListObserver;
import com.marc_auberer.musicmanager.application.service.SongService;
import com.marc_auberer.musicmanager.application.service.YTLinkGeneratorService;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.user.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MusicManagerUI extends JFrame implements SongListObserver {

    private final LoginObserver loginObserver;
    private final User user;
    private final SongService songService;
    private final YTLinkGeneratorService linkGeneratorService;
    private JTable songTable;
    private final Optional<Song> selectedSong = Optional.empty();

    public MusicManagerUI(LoginObserver loginObserver, User user) {
        this.loginObserver = loginObserver;
        this.user = user;

        // Initialize services
        songService = new SongService(user, this);
        linkGeneratorService = new YTLinkGeneratorService();

        setupUI();
    }

    private void setupUI() {
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

        // Song list
        String[] columnNames = {"Song Title", "Artist", "Genre", "Bpm", "Bar type"};
        songTable = new JTable(new String[][]{}, columnNames);
        JScrollPane songScrollPane = new JScrollPane(songTable);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        constraints.gridheight = 4;
        constraints.weightx = 5;
        rootPanel.add(songScrollPane, constraints);

        // New button
        JButton buttonNew = new JButton("New Song");
        buttonNew.addActionListener(e -> newSong());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        rootPanel.add(buttonNew, constraints);

        // Edit button
        JButton buttonEdit = new JButton("Edit Song");
        buttonEdit.setEnabled(false);
        buttonEdit.addActionListener(e -> editSong());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.weightx = 1;
        rootPanel.add(buttonEdit, constraints);

        // Delete button
        JButton buttonDelete = new JButton("Delete Song");
        buttonDelete.setEnabled(false);
        buttonDelete.addActionListener(e -> deleteSong());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.weightx = 1;
        rootPanel.add(buttonDelete, constraints);

        // Play button
        JButton buttonPlay = new JButton("Play Song");
        buttonPlay.setEnabled(false);
        buttonPlay.addActionListener(e -> playSong());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.weightx = 1;
        rootPanel.add(buttonPlay, constraints);

        // Logout button
        JButton buttonLogout = new JButton("LogOut");
        buttonLogout.addActionListener(e -> triggerLogout());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 4;
        constraints.gridy = 4;
        constraints.weightx = 1;
        rootPanel.add(buttonLogout, constraints);
    }

    private void newSong() {
        // Open JFrame to add new song
    }

    private void playSong() {
        assert selectedSong.isPresent();

        // Assemble YouTube URL
        String url = linkGeneratorService.generateYouTubeLink(selectedSong.get());

        // Open Website
        try {
            Desktop.getDesktop().browse(URI.create(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editSong() {
        assert selectedSong.isPresent();
    }

    private void deleteSong() {
        assert selectedSong.isPresent();
        songService.delete(selectedSong.get());
    }

    private void triggerLogout() {
        dispose();
        loginObserver.onLogout();
    }

    @Override
    public void onRefresh(List<Song> songList) {
        // Convert the List into table rows and cells
        String[][] songData = songList.stream().map(song -> {
            String songTitle = song.getTitle();
            String songArtists = song.getArtists().stream()
                    .map(artist -> artist.getFirstName() + " " + artist.getLastName())
                    .collect(Collectors.joining(", "));
            String songGenre = song.getGenre().getName();
            String songBpm = String.valueOf(song.getBpm());
            String songBarType = song.getBarType().getBeatCount() + "/" + song.getBarType().getBeatValue();
            return new String[]{songTitle, songArtists, songGenre, songBpm, songBarType};
        }).toArray(String[][]::new);

        // Display the data
        for (int y = 0; y < songData.length; y++) {
            for (int x = 0; x < songData[y].length; y++) {
                songTable.getModel().setValueAt(songData[y][x], x, y);
            }
        }
    }
}
