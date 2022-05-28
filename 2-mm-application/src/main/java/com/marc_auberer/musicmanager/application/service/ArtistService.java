package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.domain.artist.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtists();

    void createArtist(Artist artist);

}
