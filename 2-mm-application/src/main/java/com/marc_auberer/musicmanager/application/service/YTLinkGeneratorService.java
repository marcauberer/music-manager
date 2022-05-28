package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.domain.song.Song;

public interface YTLinkGeneratorService {

    String generateYouTubeLink(Song song);

}
