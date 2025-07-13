package com.quickdomain.api.gpt;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.quickdomain.exception.GptResponseFormatException;
import com.quickdomain.util.ProviderReader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Concrete implementation for <code>GptProvider.OPEN_AI</code>
 * @see GptStrategy
 */
public class OpenAiStrategy implements GptStrategy, GptStrategyFactory {

    private String apiKey;

    /**
     * @see GptStrategy
     */
    @Override
    public Map<String, List<String>> postPrompt(String p){
        String requestBody = configuredRequestBody(p);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(ProviderReader.loadApi("open-ai")))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String domainContent = getDomainContent(response);
            return GptResponseMapper.map(domainContent);
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @see GptStrategy
     */
    @Override
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @see GptStrategy
     */
    @Override
    public boolean hasApiKey() {
        return apiKey == null;
    }

    /**
     * @see GptStrategy
     */
    @Override
    public String configuredRequestBody(String content) {
        return """
            {
              "model": "gpt-3.5-turbo",
              "messages": [
                {"role": "user", "content": "%s"}
              ],
              "max_tokens": 100,
              "temperature": 0.7
            }
            """.formatted(content.replace("\n", "\\n").replace("\"", "\\\""));
    }

    /**
     * @see GptStrategy
     */
    @Override
    public String getDomainContent(HttpResponse<String> response) {
        String json = response.body();
        try {
            JSONObject obj = new JSONObject(json);
            return obj.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } catch (JSONException e) {
            throw new GptResponseFormatException(
                """
                could not get content from response because actual JSON format is different than expected.
                Actual: %s
                """.formatted(json));
        }
    }

    /**
     * @see GptStrategyFactory#create()
     */
    @Override
    public GptStrategy create() {
        return new OpenAiStrategy();
    }
}