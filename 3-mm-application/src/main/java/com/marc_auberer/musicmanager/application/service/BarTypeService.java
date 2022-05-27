package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.application.observer.BarTypeListObserver;
import com.marc_auberer.musicmanager.db.BarTypeRepositoryImpl;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.bartype.BarTypeRepository;

import java.util.List;

public class BarTypeService {

    private final BarTypeRepository barTypeRepository;
    private final BarTypeListObserver barTypeListObserver;

    public BarTypeService(BarTypeListObserver barTypeListObserver) {
        this.barTypeListObserver = barTypeListObserver;
        this.barTypeRepository = new BarTypeRepositoryImpl();
        // Notify observers about initial data load
        barTypeListObserver.onBarTypeListChanged(getAllBarTypes());
    }

    public List<BarType> getAllBarTypes() {
        return barTypeRepository.findAllBarTypes();
    }

    public void createBarType(BarType barType) {
        barTypeRepository.save(barType);
        barTypeListObserver.onBarTypeListChanged(getAllBarTypes());
    }
}
