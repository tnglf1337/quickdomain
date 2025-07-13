package com.quickdomain.core.domain;

import com.quickdomain.api.gpt.GptResponseMapper;
import com.quickdomain.api.gpt.GptService;
import com.quickdomain.api.gpt.GptStrategy;
import com.quickdomain.api.gpt.OpenAiStrategy;
import com.quickdomain.prompt.SimpleDomainPrompt;
import com.quickdomain.util.CsvReader;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The generator class for a simple domain. A simple domain is a domain where members of <code>T</code> are primitive types.
 * It is not possible to create a SimpleDomain of <code>T</code> when <code>T</code> has reference types.
 * <br>
 * The data gets fetched depending on the selected {@link GptStrategy}. Default is {@link OpenAiStrategy}
 *
 * @author Timo Neske
 * @since 1.0.0
 * @param <T> is the class of which a domain should be generated.
 */
public class SimpleDomain<T> implements Domain<T> {
    private int numberOfEntities;
    private Class<T> domainClass;
    private GptService gptService;

    public SimpleDomain(Class<T> domain, int numberOfEntities) {
        validate(domain, numberOfEntities);
        this.domainClass = domain;
        this.numberOfEntities = numberOfEntities;
        this.gptService = new GptService();
    }
    public Field[] getDomainFields() {
        return domainClass.getDeclaredFields();
    }

    /**
     * Initialize the dummy domain based on the prompt and numberOfEntities passed before.
     * @return List with generated entitities of type T
     */
    @Override
    public List<T> generate(){
        String prompt = new SimpleDomainPrompt().withMeta(getDomainFields(), Locale.GERMANY, numberOfEntities).build();
        Map<String, List<String>> gptResponse = gptService.postPrompt(prompt);
        DomainConstructor<T> constructor = new DomainConstructor<>(domainClass, numberOfEntities, gptResponse);
        return constructor.constructSimple();
    }

    @Override
    public List<T> generate(String content) {
        Map<String, List<String>> contentMap = GptResponseMapper.map(content);
        DomainConstructor<T> constructor = new DomainConstructor<>(domainClass, numberOfEntities, contentMap);
        return constructor.constructSimple();
    }

    @Override
    public List<T> generate(Path filePath) {
        String content = CsvReader.readCsvContent(filePath);
        Map<String, List<String>> contentMap = GptResponseMapper.map(content);
        DomainConstructor<T> constructor = new DomainConstructor<>(domainClass, numberOfEntities, contentMap);
        return constructor.constructSimple();
    }

    @Override
    public void validate(Class<T> domain, int n) {
        DomainValidator.validateSimpleDomain(domain, n);
    }

    /**
     * @see GptStrategy
     */
    public void changeStrategy(GptStrategy strategy) {
        gptService.setStrategy(strategy);
    }


    /**
     * @see GptService
     */
    void setApiKey(String apiKey) {
        gptService.setApiKey(apiKey);
    }

    /**
     * @see GptStrategy
     */
    public boolean noStrategySet() {
        return gptService.noStrategySet();
    }

    /**
     * @see GptStrategy
     */
    public boolean noApiKeySet() {
        return gptService.noApiKeySet();
    }


}