package com.marc_auberer.musicmanager.domain.artist;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository {
    Optional<Artist> findArtistById(long id);

    List<Artist> findAllArtists();

    void save(Artist artist);

    void delete(long id);
}