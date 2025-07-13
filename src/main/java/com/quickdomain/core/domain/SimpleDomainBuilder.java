package com.quickdomain.core.domain;

import com.quickdomain.api.gpt.GptProvider;
import com.quickdomain.api.gpt.GptStrategyFactory;
import com.quickdomain.api.gpt.OpenAiStrategy;
import com.quickdomain.exception.DomainBuildException;
import java.util.List;

public class SimpleDomainBuilder<T> {
    private SimpleDomain<T> simpleDomain;
    private GptStrategyFactory gptStrategyFactory;

    public SimpleDomainBuilder(GptStrategyFactory gptStrategyFactory) {
        this.gptStrategyFactory = gptStrategyFactory;
    }

    public SimpleDomainBuilder<T> of(Class<T> domain, int numberOfEntities) {
        simpleDomain = new SimpleDomain<>(domain, numberOfEntities);
        return this;
    }

    public SimpleDomainBuilder<T> andProvider(GptProvider provider) {
        switch (provider) {
            case OPEN_AI -> simpleDomain.changeStrategy(gptStrategyFactory.create());
            default -> simpleDomain.changeStrategy(new OpenAiStrategy());
        }
        return this;
    }

    public SimpleDomainBuilder<T> andKey(String apiKey) {
        simpleDomain.setApiKey(apiKey);
        return this;
    }

    public List<T> generate() {
        validBuild();
        return simpleDomain.generate();
    }

    public SimpleDomain<T> build() {
        return simpleDomain;
    }

    private void validBuild() {
        if(simpleDomain.noStrategySet()) throw new DomainBuildException("cannot build domain without strategy set");
        if(simpleDomain.noApiKeySet()) throw new DomainBuildException("cannot build domain without api key set");
    }
}