package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.domain.song.Song;

import java.util.List;

public interface SongService {

    List<Song> getAllSongsForUser();

    /**
     * Search a song by its title.
     * Note: The search term needs to be a fragment of the song title to find it. The search is not case-sensitive
     *
     * @param searchTerm Search term for the search
     * @return List of songs, that match the search criteria
     */
    List<Song> searchSongsByTitle(String searchTerm);

    void create(Song song);

    void update(Song song);

    void delete(Song song);

}
