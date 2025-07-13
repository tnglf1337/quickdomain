package com.quickdomain.api.gpt;

import com.quickdomain.exception.InvalidResponseContentException;

import java.util.*;
import static com.quickdomain.api.gpt.GptResponseFormatValidator.*;

/**
 * Manages the mapping of the response content from the GPT providers.
 */
public class GptResponseMapper {

    /**
     * Maps the response content to a <code>Map</code> with field identifiers as keys and lists of values as field values.
     * @param domainContent is the response content from the GPT provider.
     * @return a <code>Map</code> with field identifiers as keys and lists of values as values.
     * @throws InvalidResponseContentException if the content is not in the desired format.
     */
    public static Map<String, List<String>> map(String domainContent) {
        Map<String, List<String>> map = new LinkedHashMap<>();
        String[] lines = domainContent.split("\n");
        for (String line : lines) {
            if(!isValidContent(line)) throw new InvalidResponseContentException("line of content '%s' is not in desired format: 'str1,str2,str3,more'");
            String[] splittedLine = line.split(",");
            String fieldIDentifier = splittedLine[0];
            List<String> values = new ArrayList<>(Arrays.asList(splittedLine).subList(1, splittedLine.length));
            map.put(fieldIDentifier, values);
        }

        return map;
    }
}