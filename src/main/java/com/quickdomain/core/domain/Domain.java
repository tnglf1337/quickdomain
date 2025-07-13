package com.quickdomain.core.domain;

import java.nio.file.Path;
import java.util.List;

/**
 * This interface declares the functions each Domain-Object should implement.
 *
 * @param <T> is the domain object of the application for which entities should be generated.
 * @since 1.0.0
 * @author Timo Neske
 */
public interface Domain<T> {
    /**
     * This method implements the logic to generate the entities for the domain.
     * @return List<T> is the list with <code>n</code> generated entities.
     */
    List<T> generate();

    /**
     * This method implements the logic to generate the entities for the domain by a raw <code>String</code> content.
     * @param content is the content to generate entities from. The string must be in format: <p><code>'fieldName1,val1,val2\nfieldName2,val1,val2'</code></p> fieldNames must be separated by '\n'.
     * @return List<T> is the list with <code>n</code> generated entities.
     */
    List<T> generate(String content);

    /**
     * This method implements the logic to generate the entities for the domain by a file path to an Csv file. More information at {@link com.quickdomain.util.CsvReader}
     * @param filePath is the relative path to the csv file to read from.
     * @return List<T> is the list with <code>n</code> generated entities.
     */
    List<T> generate(Path filePath);

    /**
     * Implementation has to validate class variable types of <code>T</code>.
     * @param domain is the given domain <code>T</code> to validate.
     * @param n is the number of entities to generate
     */
    void validate(Class<T> domain, int n);
}