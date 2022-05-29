package com.marc_auberer.musicmanager.application.service.impl;

import com.marc_auberer.musicmanager.application.service.YTLinkGeneratorService;
import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.song.Song;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class YTLinkGeneratorServiceImpl implements YTLinkGeneratorService {

    private static final String URL_TEMPLATE = "https://www.youtube.com/results?search_query=%s+%s";

    @Override
    public String generateYouTubeLink(Song song) {
        String songTitle = song.getTitle();
        Artist firstArtist = song.getArtists().get(0);
        String firstArtistName = firstArtist.getFirstName();
        if (!firstArtist.getLastName().isEmpty()) {
            firstArtistName += " " + firstArtist.getLastName();
        }
        return String.format(URL_TEMPLATE, encodeValue(firstArtistName), encodeValue(songTitle));
    }

    private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
