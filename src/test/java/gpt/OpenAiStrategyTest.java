package gpt;

import com.quickdomain.exception.GptResponseFormatException;
import com.quickdomain.api.gpt.GptStrategy;
import com.quickdomain.api.gpt.GptStrategyFactory;
import com.quickdomain.api.gpt.OpenAiStrategy;
import com.quickdomain.prompt.SimpleDomainPrompt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.User;

import java.lang.reflect.Field;
import java.net.http.HttpResponse;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OpenAiStrategyTest {

    private static GptStrategy openAiStrategy;

    @BeforeAll
    public static void setUp() {
        openAiStrategy = new OpenAiStrategy();
    }

    @Test
    @DisplayName("Ein JSON-Request-Body String wird mit gewünschtem Inhalt konfiguriert")
    void test1() {
        Field[] fields = User.class.getDeclaredFields();
        String content = new SimpleDomainPrompt().withMeta(fields, Locale.GERMANY, 5).build();
        String expectedContent = """
                        Generiere n=5 zufällige Werte für folgende m=3 Variablen:
                        - 'username'
                        - 'email'
                        - 'age'
                        Das Format der Ausgabe muss folgendermaßen sein:
                        - Für die Variable i<=m: 'variable,wert1,wert2,wert3,wert4' und so weiter bis n.
                        - Achte darauf, dass die generierten Werte keine Duplikate sind.
                        Generiere auf keinen Fall anderen Output, außer wenn ein Fehler bei dir passiert: -1""";

        String expected = """
            {
              "model": "gpt-3.5-turbo",
              "messages": [
                {"role": "user", "content": "%s"}
              ],
              "max_tokens": 100,
              "temperature": 0.7
            }
            """.formatted(expectedContent.replace("\n", "\\n").replace("\"", "\\\""));

        String actual = openAiStrategy.configuredRequestBody(content);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Der generierte Content-String der Gpt wird aus der HttpResponse geholt")
    void test2() {
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.body()).thenReturn("""
                {
                  "id": "chatcmpl-7abcXYZ...",
                  "object": "chat.completion",
                  "created": 1658792345,
                  "model": "gpt-3.5-turbo",
                  "choices": [
                    {
                      "index": 0,
                      "message": {
                        "role": "assistant",
                        "content": "username,user1,user2\\nemail,user1@exmaple.com,user2@example.com"
                      },
                      "finish_reason": "stop"
                    }
                  ],
                  "usage": {
                    "prompt_tokens": 25,
                    "completion_tokens": 9,
                    "total_tokens": 34
                  }
                }
                """);
        String expected = "username,user1,user2\nemail,user1@exmaple.com,user2@example.com";

        String actual = openAiStrategy.getDomainContent(mockResponse);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Wenn der Provider nicht die expected JSON-Struktur mit dem content-String liefert, wird eine Exception geworfen")
    void test3() {
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.body()).thenReturn("""
                {
                  "unexpected content"
                }
                """);

        assertThrows(GptResponseFormatException.class, () -> openAiStrategy.getDomainContent(mockResponse));
    }

    @Test
    @DisplayName("create() liefert ein OpenAiStrategy-Instanz")
    void test4() {
        GptStrategyFactory factory = new OpenAiStrategy();
        GptStrategy actual = factory.create();
        assertThat(actual).isNotNull();
        assertThat(actual.getClass()).isEqualTo(OpenAiStrategy.class);
    }
}
