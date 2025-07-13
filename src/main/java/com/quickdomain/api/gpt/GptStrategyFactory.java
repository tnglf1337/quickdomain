package com.quickdomain.api.gpt;

/**
 * Factory interface for creating instances of {@link GptStrategy}.
 * This allows for the creation of different strategies based on the provider.
 *
 * @since 1.0.0
 * @author Timo Neske
 */
public interface GptStrategyFactory {

    /**
     * @return a new instance of {@link GptStrategy} based on the provider.
     */
    GptStrategy create();
}
