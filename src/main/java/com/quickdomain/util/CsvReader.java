package com.quickdomain.util;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading CSV files containing domain entity data.
 *
 * @since 1.0.0
 * @author Timo Neske
 */
public class CsvReader {
    /**
     * Read a csv-file with the content for the domain entities.
     * Each line of the file must have this format
     * <p><code>field identifier,val1,val2,val3,...</code></p>
     * followed by all other field identifier of the entity.
     * @param filePath the path to the csv file
     * @return the content of the csv file as a single string, with each line separated by a newline character
     */
    public static String readCsvContent(Path filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {
            List<String> zeilen = new ArrayList<>();
            String zeile;
            while ((zeile = br.readLine()) != null) {
                zeilen.add(zeile);
            }

            if (zeilen.isEmpty()) {
                throw new IllegalArgumentException("file '%s 'could not be read because it is empty ".formatted(filePath.toFile().getAbsolutePath()));
            }
            return String.join("\n", zeilen);
        } catch (IOException e) {
            throw new UncheckedIOException("error reading file: '%s'".formatted(filePath.getFileName().toString()), e);
        }
    }
}