package com.marc_auberer.musicmanager.application.observer;

import com.marc_auberer.musicmanager.domain.artist.Artist;

import java.util.List;

public interface ArtistListObserver {
    void onArtistListChanged(List<Artist> artistList);
}
