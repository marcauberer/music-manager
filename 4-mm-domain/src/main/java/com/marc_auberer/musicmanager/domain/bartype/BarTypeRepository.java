package com.marc_auberer.musicmanager.domain.bartype;

import java.util.List;
import java.util.Optional;

public interface BarTypeRepository {
    Optional<BarType> findBarTypeById(long id);

    List<BarType> findAllBarTypes();

    void save(BarType barType);

    void delete(long id);
}