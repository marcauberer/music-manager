package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.application.observer.ArtistListObserver;
import com.marc_auberer.musicmanager.db.ArtistRepositoryImpl;
import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.artist.ArtistRepository;
import com.marc_auberer.musicmanager.domain.song.Song;

import java.util.List;

public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistListObserver artistListObserver;

    public ArtistService(ArtistListObserver artistListObserver) {
        this.artistListObserver = artistListObserver;
        this.artistRepository = new ArtistRepositoryImpl();
        // Notify observers about initial data load
        artistListObserver.onArtistListChanged(getAllArtists());
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAllArtists();
    }

    public List<Artist> getAllArtistsBySong(Song song) {
        return artistRepository.findArtistsBySongId(song.getId());
    }

    public void createArtist(Artist artist) {
        artistRepository.save(artist);
        artistListObserver.onArtistListChanged(getAllArtists());
    }
}
