package com.quickdomain.api.gpt;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

/**
 * Strategy interface for communicating with supported GPT providers.
 * <p>
 * Defines the core methods needed to send prompts to the selected GPT providers api.
 * </p>
 *
 * @author Timo Neske
 * @since 1.0.0
 */
public interface GptStrategy {

    /**
     * Sends a prompt to the GPT model and returns the response.
     *
     * @param p the prompt to send
     * @return a map where keys represent class variable identifiers and the items are the generated values.
     */
    Map<String, List<String>> postPrompt(String p);

    /**
     * Sets the API key used to authenticate requests to the GPT provider.
     *
     * @param apiKey the API key provided by the provider.
     */
    void setApiKey(String apiKey);

    /**
     * Configures the request body for the gpt api endpoint. Further information at the api docs of the desired provider.
     * @param content The content for the {@link HttpRequest}.
     * @return {@link String} the request body for the {@link HttpRequest}
     */
    String configuredRequestBody(String content);

    /**
     * Get the content that the gpt generated based on the prompt.
     * @param response The {@link HttpResponse<String>} object to extract the content from.
     * @return {@link String} the generated content.
     */
    String getDomainContent(HttpResponse<String> response);

    /**
     * Checks whether an api key is set.
     * @return <code>true</code> if an api key is set <code>else</code> otherwise.
     */
    boolean hasApiKey();
}


