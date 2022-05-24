package com.marc_auberer.musicmanager.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CSVParser {

    private final Path filePath;
    private final String delimiter;

    public CSVParser(Path filePath, String delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    public Optional<List<String[]>> parse() {
        try {
            List<String> rows = Files.readAllLines(filePath);

            // Loop through all rows
            List<String[]> result = new ArrayList<>();
            for (int i = 1; i < rows.size(); ++i) {
                // Split row by delimiter to get cells
                String[] cells = rows.get(i).split(delimiter);
                // Trim all cells
                for (int j = 0; j < cells.length; j++) {
                    cells[i] = cells[i].trim();
                }
                // Add to result
                result.add(cells);
            }

            return Optional.of(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
