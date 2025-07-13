package com.quickdomain.core.domain;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     * @throws InterruptedException
     */
    List<T> generate() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, InterruptedException;


    List<T> generate(String content) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    List<T> generate(Path filePath) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    /**
     * Implementation has to validate class variable types of <code>T</code>.
     * @param domain is the given domain <code>T</code> to validate.
     * @param n is the number of entities to generate
     */
    void validate(Class<T> domain, int n);
}
