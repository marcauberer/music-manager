package com.marc_auberer.musicmanager.domain.relation;

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
}
