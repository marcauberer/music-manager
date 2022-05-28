package com.marc_auberer.musicmanager.service;

import com.marc_auberer.musicmanager.application.service.SongService;
import com.marc_auberer.musicmanager.application.service.impl.SongServiceImpl;
import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.song.SongRepository;
import com.marc_auberer.musicmanager.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.marc_auberer.musicmanager.domain.AbstractRepository.AUTO_INC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class SongServiceTest {

    @Mock
    private User user;
    @Mock
    private SongRepository songRepository;

    @BeforeEach
    protected void prepareTests() {
        MockitoAnnotations.openMocks(this);

        // User getId()
        when(user.getId()).thenReturn(3L);

        // SongRepository findGenreById
        List<Artist> artists = List.of(new Artist(0, "Slipknot", "", new Date(0)));
        Genre genre = new Genre(0, "Metal");
        BarType barType = new BarType(0, 4, 4);
        Song mockSong = new Song(12, 3, "Unsainted", artists, genre, 101, barType);
        when(songRepository.findSongById(0)).thenReturn(Optional.of(mockSong));

        // SongRepository findAllSongsByUserId
        List<Song> mockSongs = List.of(mockSong);
        when(songRepository.findAllSongsByUserId(3)).thenReturn(mockSongs);

        // SongRepository create()
        doNothing().when(songRepository).save(any(Song.class));

        // SongRepository update()
        doNothing().when(songRepository).update(any(Song.class));

        // SongRepository delete()
        doNothing().when(songRepository).delete(anyLong());
    }

    @Test
    protected void getAllSongsForUser() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        SongService songService = new SongServiceImpl(user, songRepository, songList -> changeCounter.getAndIncrement());

        // Execute
        List<Song> actualResult = songService.getAllSongsForUser();

        // Assert
        List<Artist> artists = List.of(new Artist(0, "Slipknot", "", new Date(0)));
        Genre genre = new Genre(0, "Metal");
        BarType barType = new BarType(0, 4, 4);
        Song expectedSong = new Song(12, 3, "Unsainted", artists, genre, 101, barType);
        assertEquals(List.of(expectedSong), actualResult);
        assertEquals(1, changeCounter.get());
    }

    @Test
    protected void searchSongsByTitle() {

    }

    @Test
    protected void createSong() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        SongService songService = new SongServiceImpl(user, songRepository, songList -> changeCounter.getAndIncrement());
        List<Artist> artists = List.of(new Artist(1, "Arch Enemy", "", new Date(0)));
        Genre genre = new Genre(0, "Melodic Death Metal");
        BarType barType = new BarType(0, 4, 4);
        Song newSong = new Song(AUTO_INC, 3, "No Gods, No Masters", artists, genre, 124, barType);

        // Execute
        songService.create(newSong);

        // Verify
        verify(songRepository, times(1)).save(newSong);

        // Assert
        assertEquals(2, changeCounter.get());
    }

    @Test
    protected void updateSong() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        SongService songService = new SongServiceImpl(user, songRepository, songList -> changeCounter.getAndIncrement());
        List<Artist> artists = List.of(new Artist(0, "Slipknot", "", new Date(0)));
        Genre genre = new Genre(0, "Metal");
        BarType barType = new BarType(0, 4, 4);
        Song updatedSong = new Song(12, 3, "Psychosocial", artists, genre, 135, barType);

        // Execute
        songService.update(updatedSong);

        // Verify
        verify(songRepository, times(1)).update(updatedSong);

        // Assert
        assertEquals(2, changeCounter.get());
    }

    @Test
    protected void deleteSong() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        SongService songService = new SongServiceImpl(user, songRepository, songList -> changeCounter.getAndIncrement());
        List<Artist> artists = List.of(new Artist(0, "Slipknot", "", new Date(0)));
        Genre genre = new Genre(0, "Metal");
        BarType barType = new BarType(0, 4, 4);
        Song deleteSong = new Song(12, 3, "Unsainted", artists, genre, 101, barType);

        // Execute
        songService.delete(deleteSong);

        // Verify
        verify(songRepository, times(1)).delete(12);
    }
}
