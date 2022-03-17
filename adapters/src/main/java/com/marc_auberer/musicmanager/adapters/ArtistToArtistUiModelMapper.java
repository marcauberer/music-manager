package com.marc_auberer.musicmanager.adapters;

import com.marc_auberer.musicmanager.domain.artist.Artist;

import java.util.function.Function;

public class ArtistToArtistUiModelMapper implements Function<Artist, ArtistUiModel> {

    @Override
    public ArtistUiModel apply(Artist artist) {
        return new ArtistUiModel(
                artist.getId(),
                artist.getFirstName(),
                artist.getLastName(),
                artist.getDateOfBirth()
        );
    }
}
