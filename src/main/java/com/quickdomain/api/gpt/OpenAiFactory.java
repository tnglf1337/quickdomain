package com.quickdomain.api.gpt;

/**
 * Factory for creating instances of {@link OpenAiStrategy}.
 *
 * @since 1.0.0
 * @author Timo Neske
 */
public class OpenAiFactory implements GptStrategyFactory{

    /**
     * @see GptStrategyFactory#create()
     */
    @Override
    public GptStrategy create() {
        return new OpenAiStrategy();
    }
}
