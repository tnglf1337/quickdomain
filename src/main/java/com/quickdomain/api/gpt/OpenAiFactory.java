package com.quickdomain.api.gpt;

public class OpenAiFactory implements GptStrategyFactory{
    @Override
    public GptStrategy create() {
        return new OpenAiStrategy();
    }
}
