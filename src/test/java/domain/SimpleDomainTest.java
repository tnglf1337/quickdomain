package domain;

import com.quickdomain.api.gpt.*;
import com.quickdomain.core.domain.SimpleDomain;
import com.quickdomain.core.domain.SimpleDomainBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.*;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class SimpleDomainTest {
    @Test
    @DisplayName("Aus einer Domain, die nur aus Wrapper-Klassen betsteht, wird erfolgreich die Dummy-Domain generiert")
    void test1(){
        List<FullDummyWrapperEntity> expected = EntityMother.getFullDummyWrapperEntities();

        Map<String, List<String>> stubMap = new LinkedHashMap<>();
        stubMap.put("str", List.of("Hallo Welt", "Hello World"));
        stubMap.put("charVal", List.of("A", "B"));
        stubMap.put("intVal", List.of("42", "84"));
        stubMap.put("longVal", List.of("1234567890", "987654321"));
        stubMap.put("doubleVal", List.of("3.1415", "2.71828"));
        stubMap.put("floatVal", List.of("2.718", "3.1415"));
        stubMap.put("shortVal", List.of("1000", "2000"));
        stubMap.put("byteVal", List.of("12", "34"));

        GptStrategyFactory gptStrategyFactory = mock(GptStrategyFactory.class);
        GptStrategy openAiStrategy = mock(OpenAiStrategy.class);
        when(gptStrategyFactory.create()).thenReturn(openAiStrategy);
        when(openAiStrategy.postPrompt(anyString())).thenReturn(stubMap);

        SimpleDomainBuilder<FullDummyWrapperEntity> builder = new SimpleDomainBuilder<>(gptStrategyFactory);
        List<FullDummyWrapperEntity> actual = builder.of(FullDummyWrapperEntity.class, 2).andProvider(GptProvider.OPEN_AI).andKey("apikey").generate();

        assertThat(actual).isEqualTo(expected);
        verify(openAiStrategy, times(1)).postPrompt(anyString());
    }

    @Test
    @DisplayName("Aus einer Domain, die nur aus primitiven Datentypen betsteht, wird erfolgreich die Dummy-Domain generiert")
    void test2(){
        List<FullDummyPrimitiveEntitiy> expected = EntityMother.getFullDummyPrimitiveEntities();

        Map<String, List<String>> stubMap = new LinkedHashMap<>();
        stubMap.put("charVal", List.of("A", "B"));
        stubMap.put("intVal", List.of("42", "84"));
        stubMap.put("longVal", List.of("1234567890", "9876543210"));
        stubMap.put("doubleVal", List.of("3.1415", "1.618"));
        stubMap.put("floatVal", List.of("2.718", "1.4142"));
        stubMap.put("shortVal", List.of("1000", "2000"));
        stubMap.put("byteVal", List.of("12", "34"));
        stubMap.put("boolVal", List.of("true", "false"));

        GptStrategyFactory gptStrategyFactory = mock(GptStrategyFactory.class);
        GptStrategy openAiStrategy = mock(OpenAiStrategy.class);
        when(gptStrategyFactory.create()).thenReturn(openAiStrategy);
        when(openAiStrategy.postPrompt(anyString())).thenReturn(stubMap);

        SimpleDomainBuilder<FullDummyPrimitiveEntitiy> builder = new SimpleDomainBuilder<>(gptStrategyFactory);
        List<FullDummyPrimitiveEntitiy> actual = builder.of(FullDummyPrimitiveEntitiy.class, 2).andProvider(GptProvider.OPEN_AI).andKey("apikey").generate();

        assertThat(actual).isEqualTo(expected);
        verify(openAiStrategy, times(1)).postPrompt(anyString());
    }

    @Test
    @DisplayName("Aus einer Domain, die nur gemischten Datentypen betsteht, wird erfolgreich die Dummy-Domain generiert")
    void test3() {
        List<FullDummyMixedEntity> expected = EntityMother.getFullDummyMixedEntities();

        Map<String, List<String>> stubMap = new LinkedHashMap<>();
        stubMap.put("name", List.of("Alice", "Bob"));
        stubMap.put("age", List.of("30", "25"));
        stubMap.put("score", List.of("95.5", "82.0"));
        stubMap.put("active", List.of("true", "false"));

        GptStrategyFactory gptStrategyFactory = mock(GptStrategyFactory.class);
        GptStrategy openAiStrategy = mock(OpenAiStrategy.class);
        when(gptStrategyFactory.create()).thenReturn(openAiStrategy);
        when(openAiStrategy.postPrompt(anyString())).thenReturn(stubMap);

        SimpleDomainBuilder<FullDummyMixedEntity> builder = new SimpleDomainBuilder<>(gptStrategyFactory);
        List<FullDummyMixedEntity> actual = builder.of(FullDummyMixedEntity.class, 2).andProvider(GptProvider.OPEN_AI).andKey("apikey").generate();

        assertThat(actual).isEqualTo(expected);
        verify(openAiStrategy, times(1)).postPrompt(anyString());
    }

    @Test
    @DisplayName("Eine SimpleDomain kann direkt aus einem content-String konstruiert werden")
    void test4()  {
        String content = "username,user1,user2\nemail,user1@example.com,user2@example.com\nage,20,30";

        List<User> actual = new SimpleDomain<>(User.class, 2).generate(content);

        assertThat(actual).isEqualTo(List.of(new User("user1", "user1@example.com", 20), new User("user2", "user2@example.com", 30)));
    }

    @Test
    @DisplayName("Eine SimpleDomain wird aus dem Inhalt einer Csv-Datei konstruiert")
    void test5() {
        Path filePath = Path.of("src/test/resources/dummy-content.csv");
        List<User> actual = new SimpleDomain<>(User.class, 2).generate(filePath);

        assertThat(actual).isEqualTo(List.of(new User("user1", "user1@example.com", 20), new User("user2", "user2@example.com", 30)));
    }

    @Test
    @DisplayName("Eine SimpleDomain kann aus einer nicht existierenden Csv.Datei nciht konstruiert werden")
    void test6(){
        Path filePath = Path.of("src/test/resources/huh.csv");

        assertThrows(UncheckedIOException.class, () -> new SimpleDomain<>(User.class, 2).generate(filePath));
    }
}