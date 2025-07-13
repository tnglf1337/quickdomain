package gpt;

import com.quickdomain.api.gpt.GptResponseMapper;
import com.quickdomain.exception.InvalidResponseContentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GptResponseMapperTest {

    @Test
    @DisplayName("Der content-String der Gpt-Response wird korrekt in eine Map gemapped")
    void test1() {
        String response = "username,user1,user2\nemail,user1@example.com,user2@example.com";
        Map<String, List<String>> expected = new TreeMap<>();
        expected.put("username", List.of("user1", "user2"));
        expected.put("email", List.of("user1@example.com", "user2@example.com"));

        Map<String, List<String>> actual = GptResponseMapper.map(response);

        assertThat(actual).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("testValues")
    @DisplayName("Wenn der content-String nicht im korrekten Format ist, wird eine Exception geworfen")
    void test1(String content) {
        assertThrows(InvalidResponseContentException.class, () -> GptResponseMapper.map(content));
    }

    private List<String> testValues() {
        return List.of(
                "username,,user1,user2\nemail,user1@example.com,user2@example.com",
                "username,user1,user2\nemail,,user1@example.com,user2@example.com",
                ",username,user1,user2\nemail,user1@example.com,user2@example.com",
                "username,,user1,user2\nemail,user1@example.com,user2@example.com,");
    }
}
