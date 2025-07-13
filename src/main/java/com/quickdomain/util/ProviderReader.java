package com.quickdomain.util;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class ProviderReader {

    public static String loadApi(String provider) {
        Yaml yaml = new Yaml();
        String FILE_NAME = "provider.yaml";
        try (InputStream in = ProviderReader.class.getClassLoader().getResourceAsStream(FILE_NAME)) {
            if (in == null) throw new RuntimeException("file no found: " + FILE_NAME);
            Map<String, Object> config =  yaml.load(in);
            String api = (String) ((Map<String, Object>) ((Map<String, Object>) config.get("provider")).get(provider)).get("api");
            return api;
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Laden der YAML-Datei", e);
        }
    }
}
