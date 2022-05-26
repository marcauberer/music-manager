package com.marc_auberer.musicmanager.application.observer;

import com.marc_auberer.musicmanager.domain.song.Song;

import java.util.List;

public interface SongListObserver {
    void onRefresh(List<Song> songList);
}
