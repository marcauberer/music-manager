package com.marc_auberer.musicmanager.domain.song;

import java.util.List;
import java.util.Optional;

public interface SongRepository {
    Optional<Song> findSongById(long id);

    List<Song> findAllSongs();

    List<Song> findAllSongsByUserId(long userId);

    void save(Song song);

    void update(Song song);

    void delete(long id);
}