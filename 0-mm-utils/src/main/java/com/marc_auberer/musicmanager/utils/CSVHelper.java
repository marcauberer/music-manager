package com.marc_auberer.musicmanager.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CSVHelper {

    private final Path filePath;
    private final String delimiter;

    public CSVHelper(String filePath, String delimiter) {
        this.filePath = Path.of(filePath);
        this.delimiter = delimiter;
    }

    public Optional<List<String[]>> read() {
        try {
            // Return empty list if file does not exist
            if (!Files.exists(filePath)) {
                return Optional.of(Collections.emptyList());
            }

            // Read lines of file
            List<String> rows = Files.readAllLines(filePath);

            // Loop through all rows
            List<String[]> result = new ArrayList<>();
            for (int i = 1; i < rows.size(); ++i) {
                // Split row by delimiter to get cells
                String[] cells = rows.get(i).split(delimiter);
                // Trim all cells
                for (int j = 0; j < cells.length; j++) {
                    cells[j] = cells[j].trim();
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

    public void write(String[] header, List<String[]> rows) {
        try {
            List<String> rowStrings = new ArrayList<>();
            rowStrings.add(String.join(delimiter, header));
            rows.forEach(row -> rowStrings.add(String.join(delimiter, row)));
            String fileContent = String.join(System.lineSeparator(), rowStrings);
            Files.write(filePath, fileContent.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
