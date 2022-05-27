package com.marc_auberer.musicmanager.plugin.ui;

import com.marc_auberer.musicmanager.application.observer.ArtistListObserver;
import com.marc_auberer.musicmanager.application.observer.BarTypeListObserver;
import com.marc_auberer.musicmanager.application.observer.GenreListObserver;
import com.marc_auberer.musicmanager.application.service.ArtistService;
import com.marc_auberer.musicmanager.application.service.BarTypeService;
import com.marc_auberer.musicmanager.application.service.GenreService;
import com.marc_auberer.musicmanager.application.service.SongService;
import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.application.builder.SongBuilder;
import com.marc_auberer.musicmanager.domain.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NewEditSongDialog extends JFrame implements ArtistListObserver, GenreListObserver, BarTypeListObserver {

    // UI Components
    private JTextField songTitle;
    private JList<Artist> songArtists;
    private JComboBox<Genre> songGenre;
    private JTextField songBpm;
    private JComboBox<BarType> songBarType;

    // Members
    private final User user;
    private final SongService songService;
    private final ArtistService artistService;
    private final GenreService genreService;
    private final BarTypeService barTypeService;

    // Data collections
    private List<Artist> artists;
    private List<Genre> genres;
    private List<BarType> barTypes;

    public NewEditSongDialog(SongService songService, User user) {
        this.user = user;
        this.songService = songService;

        // Setup UI
        setupUI();

        // Create services
        this.artistService = new ArtistService(this);
        this.genreService = new GenreService(this);
        this.barTypeService = new BarTypeService(this);
    }

    private void setupUI() {
        // Setup window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 350, 400);
        setTitle("Music Manager - New song");
        setResizable(false);
        setLocationRelativeTo(null);

        // Set root layout
        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(rootPanel);

        // Prepare constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.weightx = 5;

        // Title label
        JLabel songTitleLabel = new JLabel("Song title:");
        rootPanel.add(songTitleLabel, constraints);

        // Title text field
        songTitle = new JTextField();
        constraints.gridy = 1;
        rootPanel.add(songTitle, constraints);

        // Artists label
        JLabel songArtistsLabel = new JLabel("Artists:");
        constraints.gridy = 2;
        rootPanel.add(songArtistsLabel, constraints);

        // Artists
        songArtists = new JList<>();
        JScrollPane songScrollPane = new JScrollPane(songArtists);
        constraints.gridy = 3;
        constraints.gridheight = 4;
        rootPanel.add(songScrollPane, constraints);

        // Genre label
        JLabel songGenreLabel = new JLabel("Genre:");
        constraints.gridy = 7;
        constraints.gridheight = 1;
        rootPanel.add(songGenreLabel, constraints);

        // Genre
        songGenre = new JComboBox<>();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 8;
        rootPanel.add(songGenre, constraints);

        // Bpm label
        JLabel bpmLabel = new JLabel("Bpm:");
        constraints.gridy = 9;
        rootPanel.add(bpmLabel, constraints);

        // Bpm text field
        songBpm = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 10;
        rootPanel.add(songBpm, constraints);

        // Bpm label
        JLabel barTypeLabel = new JLabel("Bar type:");
        constraints.gridy = 11;
        rootPanel.add(barTypeLabel, constraints);

        // Bar type
        songBarType = new JComboBox<>();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 12;
        rootPanel.add(songBarType, constraints);

        // Create song button
        JButton buttonCreate = new JButton("Create song");
        constraints.gridy = 13;
        rootPanel.add(buttonCreate, constraints);
        buttonCreate.addActionListener(actionEvent -> createSong());

        // Cancel button
        JButton buttonCancel = new JButton("Cancel");
        constraints.gridy = 14;
        rootPanel.add(buttonCancel, constraints);
        buttonCancel.addActionListener(actionEvent -> dispose());
    }

    private void createSong() {
        // Get title
        String title = songTitle.getText().trim();

        // Get artists
        List<Artist> selectedArtists = new ArrayList<>();
        int[] selectedIndices = songArtists.getSelectedIndices();
        for (int index : selectedIndices) {
            selectedArtists.add(artists.get(index));
        }

        SongBuilder songBuilder = new SongBuilder(user, title, selectedArtists);

        // Get genre
        int selectedGenreIndex = songGenre.getSelectedIndex();
        if (selectedGenreIndex != -1) {
            Genre genre = genres.get(selectedGenreIndex);
            songBuilder.withGenre(genre);
        }

        // Get Bpm
        if (!songBpm.getText().trim().isEmpty()) {
            float bpm = Float.parseFloat(songBpm.getText().trim());
            songBuilder.withBpm(bpm);
        }

        // Get bar type
        int selectedBarTypeIndex = songBarType.getSelectedIndex();
        if (selectedBarTypeIndex != -1) {
            BarType barType = barTypes.get(selectedBarTypeIndex);
            songBuilder.withBarType(barType);
        }

        // Build and save song
        songService.createSong(songBuilder.build());

        // Close dialog
        dispose();
    }

    @Override
    public void onArtistListChanged(List<Artist> artists) {
        this.artists = artists;
        songArtists.setListData(artists.toArray(Artist[]::new));
    }

    @Override
    public void onGenreListChanged(List<Genre> genres) {
        this.genres = genres;
        DefaultComboBoxModel<Genre> newModel = new DefaultComboBoxModel<>();
        newModel.addAll(genres);
        songGenre.setModel(newModel);
    }

    @Override
    public void onBarTypeListChanged(List<BarType> barTypes) {
        this.barTypes = barTypes;
        DefaultComboBoxModel<BarType> newModel = new DefaultComboBoxModel<>();
        newModel.addAll(barTypes);
        songBarType.setModel(newModel);
    }
}
