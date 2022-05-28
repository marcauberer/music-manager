package com.marc_auberer.musicmanager.domain.relation;

import java.util.Objects;

public class RelSongArtist {

    private final long id;
    private final long songId;
    private final long artistId;

    public RelSongArtist(long id, long songId, long artistId) {
        this.id = id;
        this.songId = songId;
        this.artistId = artistId;
    }

    public long getId() {
        return id;
    }

    public long getSongId() {
        return songId;
    }

    public long getArtistId() {
        return artistId;
    }

    public String[] getFieldContents() {
        return new String[]{String.valueOf(id), String.valueOf(songId), String.valueOf(artistId)};
    }

    public static String[] getCSVHeader() {
        return new String[]{"Id", "SongId", "ArtistId"};
    }

    @Override
    public String toString() {
        return String.format("{%s,%s,%s}", this.id, this.songId, this.artistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return ((RelSongArtist) obj).getId() == this.id;
    }
}
