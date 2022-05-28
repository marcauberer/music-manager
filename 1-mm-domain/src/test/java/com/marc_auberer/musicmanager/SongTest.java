package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.song.Song;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongTest {

    @Test
    protected void getFieldContents() {
        // Test data
        List<Artist> artists = List.of(new Artist(4, "Powerwolf", "", new Date(0)));
        Genre genre = new Genre(65, "Power Metal");
        com.marc_auberer.musicmanager.domain.bartype.BarType barType = new com.marc_auberer.musicmanager.domain.bartype.BarType(0, 4, 4);
        Song testSong = new Song(9, 12, "Demons Are A Girl's Best Friend", artists, genre, 143, barType);

        // Execute
        String[] actualResult = testSong.getFieldContents();

        // Assert
        String[] expectedResult = new String[]{"9", "12", "Demons Are A Girl's Best Friend", "65", "143.0", "0"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void getCSVHeader() {
        // Execute
        String[] actualResult = Song.getCSVHeader();

        // Assert
        String[] expectedResult = new String[]{"Id", "UserId", "Title", "Genre", "Bpm", "BarType"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void toStringNormal() {
        // Test data
        List<Artist> artists = List.of(new Artist(1, "Arch Enemy", "", new Date(0)));
        Genre genre = new Genre(61, "Melodic Death Metal");
        com.marc_auberer.musicmanager.domain.bartype.BarType barType = new com.marc_auberer.musicmanager.domain.bartype.BarType(0, 4, 4);
        Song testSong = new Song(13, 0, "Avalanche", artists, genre, 187, barType);

        // Execute
        String actualResult = testSong.toString();

        // Assert
        assertEquals("Avalanche", actualResult);
    }
}
