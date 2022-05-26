package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.db.SongRepositoryImpl;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.song.SongRepository;
import com.marc_auberer.musicmanager.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class SongService {

    private final SongRepository songRepository;
    private final User user;

    public SongService(User user) {
        this.songRepository = new SongRepositoryImpl();
        this.user = user;
    }

    public List<Song> getAllSongsForUser() {
        return songRepository.findAllSongsByUserId(user.getId());
    }

    /**
     * Search a song by its title.
     * Note: The search term needs to be a fragment of the song title to find it. The search is not case-sensitive
     *
     * @param searchTerm Search term for the search
     * @return List of songs, that match the search criteria
     */
    public List<Song> searchSongsByTitle(String searchTerm) {
        return songRepository.findAllSongsByUserId(user.getId()).stream()
                .filter(song ->
                        song.getTitle().toLowerCase().contains(searchTerm.toLowerCase())
                )
                .collect(Collectors.toList());
    }
}
