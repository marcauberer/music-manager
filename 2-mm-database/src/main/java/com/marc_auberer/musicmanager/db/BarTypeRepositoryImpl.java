package com.marc_auberer.musicmanager.db;

import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.bartype.BarTypeRepository;
import com.marc_auberer.musicmanager.utils.CSVHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BarTypeRepositoryImpl extends Repository implements BarTypeRepository {

    private static final String FILE_PATH = "./data/bar-types.csv";
    private final CSVHelper csvHelper;
    private final List<BarType> barTypes = new ArrayList<>();

    public BarTypeRepositoryImpl() {
        this(new CSVHelper(FILE_PATH, ";"));
    }

    public BarTypeRepositoryImpl(CSVHelper csvHelper) {
        this.csvHelper = csvHelper;
        // Pre-fetch all bar types at once
        reload();
    }

    @Override
    public Optional<BarType> findBarTypeById(long id) {
        return barTypes.stream().filter(barType -> barType.getId() == id).findFirst();
    }

    @Override
    public List<BarType> findAllBarTypes() {
        return barTypes;
    }

    @Override
    public void save(BarType barType) {
        // Load bar types once again to reflect any potential changes
        reload();

        // Consider auto-increment ids
        if (barType.getId() == AUTO_INC) {
            barType.setId(barTypes.size());
        }

        // Save
        barTypes.add(barType);

        // Save bar types list
        writeOut();
    }

    @Override
    public void delete(long id) {
        barTypes.removeIf(barType -> barType.getId() == id);
        writeOut();
    }

    @Override
    protected void writeOut() {
        List<String[]> serializedBarTypes = barTypes.stream()
                .map(BarType::getFieldContents)
                .collect(Collectors.toList());
        csvHelper.write(BarType.getCSVHeader(), serializedBarTypes);
    }

    @Override
    protected void reload() {
        barTypes.clear();

        // Load all bar types
        Optional<List<String[]>> serializedBarTypes = csvHelper.read();
        serializedBarTypes.ifPresent(strings -> strings.forEach(serializedBarType -> {
            // Get basic fields
            long barTypeId = Long.parseLong(serializedBarType[0]);
            int beatCount = Integer.parseInt(serializedBarType[1]);
            int beatValue = Integer.parseInt(serializedBarType[2]);

            // Create bar type object
            barTypes.add(new BarType(barTypeId, beatCount, beatValue));
        }));
    }
}
