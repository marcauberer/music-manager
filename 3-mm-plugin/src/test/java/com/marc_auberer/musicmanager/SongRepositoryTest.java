package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.db.SongRepositoryImpl;
import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.bartype.BarTypeRepository;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.genre.GenreRepository;
import com.marc_auberer.musicmanager.domain.relation.RelSongArtistRepository;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.song.SongRepository;
import com.marc_auberer.musicmanager.utils.CSVHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.marc_auberer.musicmanager.domain.AbstractRepository.AUTO_INC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SongRepositoryTest {

    @Mock
    private CSVHelper csvHelper;
    @Mock
    private GenreRepository genreRepository;
    @Mock
    private BarTypeRepository barTypeRepository;
    @Mock
    private RelSongArtistRepository relSongArtistRepository;

    @BeforeEach
    protected void prepareTest() {
        MockitoAnnotations.openMocks(this);

        // CSVHelper read()
        List<String[]> mockedReadResult = List.of(
                new String[]{"0", "0", "Ravenlight", "0", "120", "0"},
                new String[]{"1", "0", "You Will Know My Name", "1", "137", "0"},
                new String[]{"2", "0", "The Eagle Flies Alone", "1", "134", "0"}
        );
        when(csvHelper.read()).thenReturn(Optional.of(mockedReadResult));

        // CSVHelper write()
        doNothing().when(csvHelper).write(any(String[].class), any());

        // GenreRepository findGenreById()
        Genre mockGenre0 = new Genre(0, "Melodic Death Metal");
        Genre mockGenre1 = new Genre(1, "Death Metal");
        when(genreRepository.findGenreById(0)).thenReturn(Optional.of(mockGenre0));
        when(genreRepository.findGenreById(1)).thenReturn(Optional.of(mockGenre1));

        // BarTypeRepository findBarTypeById()
        BarType mockBarType = new BarType(0, 4, 4);
        when(barTypeRepository.findBarTypeById(0)).thenReturn(Optional.of(mockBarType));

        // RelSongArtistRepository findAllArtistsBySongId()
        Artist mockArtist = new Artist(0, "Arch Enemy", "", new Date(0));
        when(relSongArtistRepository.findAllArtistsBySongId(2)).thenReturn(List.of(mockArtist));
    }

    @Test
    protected void findSongById() {
        // Test data
        SongRepository songRepository = new SongRepositoryImpl(csvHelper, genreRepository,
                barTypeRepository, relSongArtistRepository);

        // Execute
        Optional<Song> optionalSong = songRepository.findSongById(1);

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertTrue(optionalSong.isPresent());
        Song song = optionalSong.get();
        assertEquals("You Will Know My Name", song.getTitle());
    }

    @Test
    protected void findAllSongs() {
        // Test data
        SongRepository songRepository = new SongRepositoryImpl(csvHelper, genreRepository,
                barTypeRepository, relSongArtistRepository);

        // Execute
        List<Song> songs = songRepository.findAllSongs();

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertEquals(3, songs.size());
        assertEquals("Ravenlight", songs.get(0).getTitle());
    }

    @Test
    protected void findAllSongsByUserId() {
        // Test data
        SongRepository songRepository = new SongRepositoryImpl(csvHelper, genreRepository,
                barTypeRepository, relSongArtistRepository);

        // Execute
        List<Song> songs = songRepository.findAllSongsByUserId(0);

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertEquals(3, songs.size());
        assertEquals("The Eagle Flies Alone", songs.get(2).getTitle());
    }

    @Test
    protected void save() {
        // Test data
        SongRepository songRepository = new SongRepositoryImpl(csvHelper, genreRepository,
                barTypeRepository, relSongArtistRepository);
        Artist artist = new Artist(1, "Arch Enemy", "", new Date(0));
        Genre genre = new Genre(0, "Melodic Death Metal");
        BarType barType = new BarType(0, 4, 4);
        Song newSong = new Song(AUTO_INC, 0, "War Eternal", List.of(artist), genre, 155, barType);

        // Execute
        songRepository.save(newSong);

        // Verify
        verify(csvHelper, times(2)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        assertEquals(4, songRepository.findAllSongs().size());
    }

    @Test
    protected void update() {
        // Test data
        SongRepository songRepository = new SongRepositoryImpl(csvHelper, genreRepository,
                barTypeRepository, relSongArtistRepository);
        Artist artist = new Artist(0, "Kamelot", "", new Date(0));
        Genre genre = new Genre(0, "Melodic Death Metal");
        BarType barType = new BarType(0, 4, 4);
        Song updatedSong = new Song(0, 0, "My Confession", List.of(artist), genre, 155, barType);

        // Execute
        songRepository.update(updatedSong);

        // Verify
        verify(csvHelper, times(1)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        List<Song> allSongs = songRepository.findAllSongs();
        assertEquals(3, allSongs.size());
        assertEquals("My Confession", allSongs.get(0).getTitle());
    }

    @Test
    protected void delete() {
        // Test data
        SongRepository songRepository = new SongRepositoryImpl(csvHelper, genreRepository,
                barTypeRepository, relSongArtistRepository);

        // Execute
        songRepository.delete(1);

        // Verify
        verify(csvHelper, times(1)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        assertEquals(2, songRepository.findAllSongs().size());
    }
}
