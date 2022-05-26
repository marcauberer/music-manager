package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.db.BarTypeRepositoryImpl;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.bartype.BarTypeRepository;

import java.util.List;

public class BarTypeService {

    private final BarTypeRepository barTypeRepository;

    public BarTypeService() {
        this.barTypeRepository = new BarTypeRepositoryImpl();
    }

    public List<BarType> getAllBarTypes() {
        return barTypeRepository.findAllBarTypes();
    }

    public void createBarType(BarType barType) {
        barTypeRepository.save(barType);
    }
}
