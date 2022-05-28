package com.marc_auberer.musicmanager.application.service.impl;

import com.marc_auberer.musicmanager.application.observer.BarTypeListObserver;
import com.marc_auberer.musicmanager.application.service.BarTypeService;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.bartype.BarTypeRepository;

import java.util.List;

public class BarTypeServiceImpl implements BarTypeService {

    private final BarTypeRepository barTypeRepository;
    private final BarTypeListObserver barTypeListObserver;

    public BarTypeServiceImpl(BarTypeRepository barTypeRepository, BarTypeListObserver barTypeListObserver) {
        this.barTypeRepository = barTypeRepository;
        this.barTypeListObserver = barTypeListObserver;
        // Notify observers about initial data load
        barTypeListObserver.onBarTypeListChanged(getAllBarTypes());
    }

    @Override
    public List<BarType> getAllBarTypes() {
        return barTypeRepository.findAllBarTypes();
    }

    @Override
    public void createBarType(BarType barType) {
        barTypeRepository.save(barType);
        barTypeListObserver.onBarTypeListChanged(getAllBarTypes());
    }
}
