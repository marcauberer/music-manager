package com.marc_auberer.musicmanager.builder;

import com.marc_auberer.musicmanager.application.builder.SongBuilder;
import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.user.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static com.marc_auberer.musicmanager.domain.AbstractRepository.AUTO_INC;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SongBuilderTest {

    @Test
    void testBuilder1() {
        // Test data
        User user = new User(0, "marc", "12345");
        List<Artist> artists = List.of(
                new Artist(0, "Caliban", "", new Date(0)),
                new Artist(0, "Christoph", "Wieczorek", new Date(0))
        );
        Genre genre = new Genre(0, "Melodic Death Metal");

        // Execute
        Song actualSong = new SongBuilder(AUTO_INC, user, "Test Song Title", artists)
                .withGenre(genre)
                .withBpm(333)
                .build();

        // Assert
        Song expectedSong = new Song(AUTO_INC, user.getId(), "Test Song Title", artists, genre, 333, null);
        assertEquals(expectedSong, actualSong);
    }

    @Test
    void testBuilder2() {
        // Test data
        User user = new User(0, "testuser", "testpw");
        List<Artist> artists = List.of(
                new Artist(0, "Amorphis", "", new Date(0))
        );
        BarType barType = new BarType(0, 5, 4);

        // Execute
        Song actualSong = new SongBuilder(AUTO_INC, user, "This Is A Test Song Title", artists)
                .withBarType(barType)
                .build();

        // Assert
        Song expectedSong = new Song(AUTO_INC, user.getId(), "Test Song Title", artists, null, 333, barType);
        assertEquals(expectedSong, actualSong);
    }
}