package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.db.GenreRepositoryImpl;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.genre.GenreRepository;
import com.marc_auberer.musicmanager.utils.CSVHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.marc_auberer.musicmanager.domain.AbstractRepository.AUTO_INC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GenreRepositoryTest {

    @Mock
    private CSVHelper csvHelper;

    @BeforeEach
    protected void prepareTest() {
        MockitoAnnotations.openMocks(this);

        // CSVHelper read()
        List<String[]> mockedReadResult = List.of(
                new String[]{"0", "Power Metal"},
                new String[]{"1", "Speed Metal"},
                new String[]{"2", "Black Metal"}
        );
        when(csvHelper.read()).thenReturn(Optional.of(mockedReadResult));

        // CSVHelper write()
        doNothing().when(csvHelper).write(any(String[].class), any());
    }

    @Test
    protected void findGenreById() {
        // Test data
        GenreRepository genreRepository = new GenreRepositoryImpl(csvHelper);

        // Execute
        Optional<Genre> optionalGenre = genreRepository.findGenreById(2);

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertTrue(optionalGenre.isPresent());
        Genre genre = optionalGenre.get();
        assertEquals("Black Metal", genre.getName());
    }

    @Test
    protected void findAllGenres() {
        // Test data
        GenreRepository genreRepository = new GenreRepositoryImpl(csvHelper);

        // Execute
        List<Genre> genres = genreRepository.findAllGenres();

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertEquals(3, genres.size());
        assertEquals("Speed Metal", genres.get(1).getName());
    }

    @Test
    protected void save() {
        // Test data
        GenreRepository genreRepository = new GenreRepositoryImpl(csvHelper);
        Genre newGenre = new Genre(AUTO_INC, "Brutal Blackened Death Metal");

        // Execute
        genreRepository.save(newGenre);

        // Verify
        verify(csvHelper, times(2)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        assertEquals(4, genreRepository.findAllGenres().size());
    }

    @Test
    protected void delete() {
        // Test data
        GenreRepository genreRepository = new GenreRepositoryImpl(csvHelper);

        // Execute
        genreRepository.delete(1);

        // Verify
        verify(csvHelper, times(1)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        assertEquals(2, genreRepository.findAllGenres().size());
    }
}
