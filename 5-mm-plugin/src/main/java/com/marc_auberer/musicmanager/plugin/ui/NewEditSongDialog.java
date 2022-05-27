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
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.marc_auberer.musicmanager.db.Repository.AUTO_INC;

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
        setBounds(0, 0, 300, 650);
        setTitle("Music Manager - New song");
        setResizable(false);
        setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();

        // Set root layout
        JPanel rootPanel = new JPanel(new GridBagLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(rootPanel);

        // Title text field
        songTitle = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.weightx = 5;
        rootPanel.add(songTitle, constraints);

        // Artists
        songArtists = new JList<>();
        JScrollPane songScrollPane = new JScrollPane(songArtists);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 5;
        constraints.gridheight = 3;
        constraints.weightx = 5;
        rootPanel.add(songScrollPane, constraints);

        // Genre
        songGenre = new JComboBox<>();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.weightx = 5;
        rootPanel.add(songGenre, constraints);

        // Bpm text field
        songBpm = new JTextField();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.weightx = 5;
        rootPanel.add(songBpm, constraints);

        // Bar type
        songBarType = new JComboBox<>();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.weightx = 5;
        rootPanel.add(songBarType, constraints);
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

        // Get genre
        int selectedGenreIndex = songGenre.getSelectedIndex();
        Genre genre = genres.get(selectedGenreIndex);

        // Get Bpm
        float bpm = Float.parseFloat(songBpm.getText().trim());

        // Get bar type
        BarType barType = null;

        // Save song
        Song song = new Song(AUTO_INC, title, selectedArtists, genre, bpm, barType);
        songService.createSong(song);

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
