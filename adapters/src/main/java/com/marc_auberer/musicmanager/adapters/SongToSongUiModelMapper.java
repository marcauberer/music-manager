package com.marc_auberer.musicmanager.adapters;

import song.Song;

import java.util.function.Function;

public class SongToSongUiModelMapper implements Function<Song, SongUiModel> {
    @Override
    public SongUiModel apply(Song song) {
        return new SongUiModel(
                song.getId(),
                song.getTitle(),
                song.getArtists(),
                song.getGenre(),
                song.getBpm(),
                song.getBarType()
        );
    }
}
