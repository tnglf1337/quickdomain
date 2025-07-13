package com.quickdomain.api.gpt;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Main class that directly interacts with the selected GPT provider
 *
 * @since 1.0.0
 * @author Timo Neske
 */
public class GptService {

    private GptStrategy strategy;

    public Map<String, List<String>> postPrompt(String prompt){
        return strategy.postPrompt(prompt);
    }

    public void setStrategy(GptStrategy strategy) {
        this.strategy = strategy;
    }

    public void setApiKey(String apiKey) {
        if(strategy != null) {
            this.strategy.setApiKey(apiKey);
        }
    }

    /**
     * Checks whether the class has a {@link GptStrategy}.
     * @return <code>true</code> if a strategy is set, <code>false</code> otherwise.
     */
    public boolean noStrategySet() {
        return strategy == null;
    }

    /**
     * @see GptStrategy
     */
    public boolean noApiKeySet() {
        return strategy.hasApiKey();
    }
}
