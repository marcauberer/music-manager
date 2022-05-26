package com.marc_auberer.musicmanager.plugin.ui;

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
import java.util.List;

import static com.marc_auberer.musicmanager.db.Repository.AUTO_INC;

public class NewEditSongDialog extends JFrame {

    // UI Components
    private JTextField songTitle;
    private JTextField songBpm;

    // Members
    private final User user;
    private final SongService songService;
    private final ArtistService artistService;
    private final GenreService genreService;
    private final BarTypeService barTypeService;

    public NewEditSongDialog(SongService songService, User user) {
        this.user = user;
        this.songService = songService;
        this.artistService = new ArtistService();
        this.genreService = new GenreService();
        this.barTypeService = new BarTypeService();

        // Setup UI
        setupUI();
    }

    private void setupUI() {
        // Setup window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 300, 600);
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

        // Artists


        // Genre


        // Bpm text field
        songBpm = new JTextField();

        // Bar type


    }

    private void createSong() {
        String title = songTitle.getText().trim();
        float bpm = Float.parseFloat(songBpm.getText().trim());

        // Save artists
        List<Artist> artists = null;

        // Save genre
        Genre genre = null;

        // Save bar type
        BarType barType = null;

        // Save song
        Song song = new Song(AUTO_INC, title, artists, genre, bpm, barType);


        // Close dialog
        dispose();
    }
}
