package com.marc_auberer.musicmanager.domain.relation;

import com.marc_auberer.musicmanager.domain.artist.Artist;

import java.util.List;

public interface RelSongArtistRepository {

    List<Artist> findAllArtistsBySongId(long songId);

    void updateRelations(List<RelSongArtist> relations);
}