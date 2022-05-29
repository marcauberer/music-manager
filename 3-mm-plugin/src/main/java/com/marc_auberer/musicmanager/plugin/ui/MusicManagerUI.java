package com.marc_auberer.musicmanager.plugin.ui;

import com.marc_auberer.musicmanager.application.observer.LoginObserver;
import com.marc_auberer.musicmanager.application.observer.SongListObserver;
import com.marc_auberer.musicmanager.application.service.impl.SongServiceImpl;
import com.marc_auberer.musicmanager.application.service.impl.YTLinkGeneratorServiceImpl;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.song.SongRepository;
import com.marc_auberer.musicmanager.domain.user.User;
import com.marc_auberer.musicmanager.plugin.repository.SongRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MusicManagerUI extends JFrame implements SongListObserver {

    // UI Components
    private JTable songTable;

    // Members
    private final LoginObserver loginObserver;
    private final User user;
    private final SongServiceImpl songService;
    private final YTLinkGeneratorServiceImpl linkGeneratorService;
    private List<Song> songs = Collections.emptyList();
    private Optional<Song> selectedSong = Optional.empty();

    public MusicManagerUI(LoginObserver loginObserver, User user) {
        this.loginObserver = loginObserver;
        this.user = user;

        setupUI();

        // Initialize services
        SongRepository songRepository = new SongRepositoryImpl();
        songService = new SongServiceImpl(user, songRepository, this);
        linkGeneratorService = new YTLinkGeneratorServiceImpl();
    }

    private void setupUI() {
        // Setup window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 900, 520);
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
        songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    editSong();
                }
            }
        });
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

        // Add selection listener to song table
        songTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedIndex = songTable.getSelectedRow();
            boolean validIndex = selectedIndex != -1;
            selectedSong = validIndex ? Optional.of(songs.get(selectedIndex)) : Optional.empty();
            buttonEdit.setEnabled(validIndex);
            buttonDelete.setEnabled(validIndex);
            buttonPlay.setEnabled(validIndex);
        });
    }

    private void newSong() {
        // Open JFrame to add new song
        JFrame newSongDialog = new NewEditSongDialog(songService, user, null);
        newSongDialog.setVisible(true);
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
        // Open JFrame to edit the selected song
        JFrame newSongDialog = new NewEditSongDialog(songService, user, selectedSong.get());
        newSongDialog.setVisible(true);
    }

    private void deleteSong() {
        assert selectedSong.isPresent();
        Song songToDelete = selectedSong.get();
        songTable.getSelectionModel().clearSelection();
        songService.delete(songToDelete);
    }

    private void triggerLogout() {
        dispose();
        loginObserver.onLogout();
    }

    @Override
    public void onSongListChanged(List<Song> songList) {
        this.songs = songList;

        // Convert the List into table rows and cells
        String[][] songData = songList.stream().map(song -> {
            // Get title
            String songTitle = song.getTitle();
            // Get artists
            String songArtists = song.getArtists().stream()
                    .map(artist -> artist.getFirstName() + " " + artist.getLastName())
                    .collect(Collectors.joining(", "));
            // Get genre
            String songGenre = "Unknown";
            if (song.getGenre() != null) {
                songGenre = song.getGenre().getName();
            }
            // Get bpm
            String songBpm = song.getBpm() == 0 ? "Unknown" : String.valueOf(song.getBpm());
            // Get bar type
            String songBarType = "Unknown";
            if (song.getBarType() != null) {
                songBarType = song.getBarType().getBeatCount() + "/" + song.getBarType().getBeatValue();
            }
            return new String[]{songTitle, songArtists, songGenre, songBpm, songBarType};
        }).toArray(String[][]::new);

        DefaultTableModel newModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        newModel.setColumnIdentifiers(new String[]{"Title", "Artists", "Genre", "Bpm", "Bar type"});
        for (String[] row : songData) {
            newModel.addRow(row);
        }
        songTable.setModel(newModel);
        newModel.fireTableDataChanged();
    }
}
