package com.marc_auberer.musicmanager.domain.artist;

import java.util.List;

public interface ArtistRepository {
    Artist findArtistById(long id);

    List<Artist> findAllArtists();

    List<Artist> findAllArtistsBySongId(long songId);

    Artist save(Artist artist);

    void delete(long id);
}