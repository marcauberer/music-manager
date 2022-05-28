package com.marc_auberer.musicmanager.service;

import com.marc_auberer.musicmanager.application.service.BarTypeService;
import com.marc_auberer.musicmanager.application.service.impl.BarTypeServiceImpl;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.bartype.BarTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BarTypeServiceTest {

    @Mock
    private BarTypeRepository barTypeRepository;

    @BeforeEach
    protected void prepareTests() {
        MockitoAnnotations.openMocks(this);

        // BarTypeRepository findBarTypeById
        BarType mockBarType = new BarType(0, 5, 6);
        when(barTypeRepository.findBarTypeById(0)).thenReturn(Optional.of(mockBarType));

        // BarTypeRepository findAllBarTypes
        List<BarType> mockBarTypes = List.of(
                mockBarType,
                new BarType(1, 3, 4),
                new BarType(2, 4, 4)
        );
        when(barTypeRepository.findAllBarTypes()).thenReturn(mockBarTypes);
    }

    @Test
    protected void getAllBarTypes() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        BarTypeService barTypeService = new BarTypeServiceImpl(barTypeRepository, barTypeList -> changeCounter.getAndIncrement());

        // Execute
        List<BarType> actualResult = barTypeService.getAllBarTypes();

        // Assert
        List<BarType> expectedResult = List.of(
                new BarType(0, 5, 6),
                new BarType(1, 3, 4),
                new BarType(2, 4, 4)
        );
        assertEquals(expectedResult, actualResult);
        assertEquals(1, changeCounter.get());
    }

    @Test
    protected void createBarType() {
        // Test data
        AtomicInteger changeCounter = new AtomicInteger();
        BarTypeService barTypeService = new BarTypeServiceImpl(barTypeRepository, barTypeList -> changeCounter.getAndIncrement());

        // Execute
        BarType newBarType = new BarType(3, 17, 8);
        barTypeService.createBarType(newBarType);

        // Assert
        assertEquals(2, changeCounter.get());
    }
}
