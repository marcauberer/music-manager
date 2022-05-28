package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.domain.bartype.BarType;

import java.util.List;

public interface BarTypeService {

    List<BarType> getAllBarTypes();

    void createBarType(BarType barType);

}
