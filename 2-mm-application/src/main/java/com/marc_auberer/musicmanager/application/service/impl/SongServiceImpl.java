package com.marc_auberer.musicmanager.application.service.impl;

import com.marc_auberer.musicmanager.application.observer.SongListObserver;
import com.marc_auberer.musicmanager.application.service.SongService;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.song.SongRepository;
import com.marc_auberer.musicmanager.domain.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final User user;
    private final SongListObserver songListObserver;

    public SongServiceImpl(User user, SongRepository songRepository, SongListObserver songListObserver) {
        this.user = user;
        this.songRepository = songRepository;
        this.songListObserver = songListObserver;
        // Notify observers about initial data load
        songListObserver.onRefresh(getAllSongsForUser());
    }

    @Override
    public List<Song> getAllSongsForUser() {
        return songRepository.findAllSongsByUserId(user.getId());
    }

    @Override
    public List<Song> searchSongsByTitle(String searchTerm) {
        return songRepository.findAllSongsByUserId(user.getId()).stream()
                .filter(song ->
                        song.getTitle().toLowerCase().contains(searchTerm.toLowerCase())
                )
                .collect(Collectors.toList());
    }

    @Override
    public void create(Song song) {
        songRepository.save(song);
        songListObserver.onRefresh(getAllSongsForUser());
    }

    @Override
    public void update(Song song) {
        songRepository.update(song);
        songListObserver.onRefresh(getAllSongsForUser());
    }

    @Override
    public void delete(Song song) {
        songRepository.delete(song.getId());
        songListObserver.onRefresh(getAllSongsForUser());
    }
}
