package com.marc_auberer.musicmanager.service;

import com.marc_auberer.musicmanager.application.service.GenreService;
import com.marc_auberer.musicmanager.application.service.impl.GenreServiceImpl;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.genre.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @BeforeEach
    protected void prepareTests() {
        MockitoAnnotations.openMocks(this);

        // GenreRepository findGenreById
        Genre mockGenre = new Genre(0, "Doom Metal");
        when(genreRepository.findGenreById(0)).thenReturn(Optional.of(mockGenre));

        // GenreRepository findAllGenres
        List<Genre> mockGenres = List.of(
                mockGenre,
                new Genre(1, "Power Metal"),
                new Genre(2, "Death Metal")
        );
        when(genreRepository.findAllGenres()).thenReturn(mockGenres);
    }

    @Test
    protected void getAllGenres() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        GenreService genreService = new GenreServiceImpl(genreRepository, genreList -> changeCounter.getAndIncrement());

        // Execute
        List<Genre> actualResult = genreService.getAllGenres();

        // Assert
        List<Genre> expectedResult = List.of(
                new Genre(0, "Doom Metal"),
                new Genre(1, "Power Metal"),
                new Genre(2, "Death Metal")
        );
        assertEquals(expectedResult, actualResult);
        assertEquals(1, changeCounter.get());
    }

    @Test
    protected void createGenre() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        GenreService barTypeService = new GenreServiceImpl(genreRepository, genreList -> changeCounter.getAndIncrement());

        // Execute
        Genre newGenre = new Genre(3, "Melodic Death Metal");
        barTypeService.createGenre(newGenre);

        // Assert
        assertEquals(2, changeCounter.get());
    }
}
