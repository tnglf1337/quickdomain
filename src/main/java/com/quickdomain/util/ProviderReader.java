package com.quickdomain.util;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

/**
 * Utility class to read the API URL for a given provider from a YAML configuration file.
 *
 * @since 1.0.0
 * @author Timo Neske
 */
public class ProviderReader {

    /**
     * Loads the API URL for the specified provider from the YAML configuration file.
     *
     * @param provider The name of the provider (e.g., "openai").
     * @return The API URL as a String.
     * @throws RuntimeException if the file is not found, or if there is an error reading the file.
     */
    public static String loadApi(String provider) {
        Yaml yaml = new Yaml();
        String FILE_NAME = "provider.yaml";
        try (InputStream in = ProviderReader.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (in == null) throw new RuntimeException("file no found: " + FILE_NAME);
            Map<String, Object> config =  yaml.load(in);

            // suppress because the structure of provider.yaml is set
            @SuppressWarnings("unchecked")
            Map<String, Object> providerMap = (Map<String, Object>) config.get("provider");

            @SuppressWarnings("unchecked")
            Map<String, Object> currentProviderMap = (Map<String, Object>) providerMap.get(provider);

	        return (String) currentProviderMap.get("api");
        } catch (Exception e) {
            throw new RuntimeException("error loading api endpoint for expected provider '%s'".formatted(provider), e);
        }
    }
}
