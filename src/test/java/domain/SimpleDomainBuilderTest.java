package domain;

import com.quickdomain.api.gpt.GptProvider;
import com.quickdomain.api.gpt.OpenAiFactory;
import com.quickdomain.exception.DomainBuildException;
import com.quickdomain.core.domain.SimpleDomain;
import com.quickdomain.core.domain.SimpleDomainBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleDomainBuilderTest {
    @Test
    @DisplayName("Eine korrekt gebildete SimpleDomain wirft keine Exception")
    void test1(){
        SimpleDomainBuilder<User> builder = new SimpleDomainBuilder<>(new OpenAiFactory());

        assertDoesNotThrow(() -> {
            SimpleDomain<User> res = builder
                    .of(User.class, 2)
                    .andProvider(GptProvider.OPEN_AI)
                    .andKey("apikey")
                    .build();
        });
    }

    @Test
    @DisplayName("Eine SimpleDomain, die ohne Provider gebaut wird, wirft eine Exception")
    void test2() {
        SimpleDomainBuilder<User> builder = new SimpleDomainBuilder<>(new OpenAiFactory());

        assertThrows(DomainBuildException.class, () -> {
            List<User> res = builder
                    .of(User.class, 2)
                    .andKey("apikey")
                    .generate();
        });
    }

    @Test
    @DisplayName("Eine SimpleDomain, die ohne Api-Key gebaut wird, wirft eine Exception")
    void test3() {
        SimpleDomainBuilder<User> builder = new SimpleDomainBuilder<>(new OpenAiFactory());

        assertThrows(DomainBuildException.class, () -> {
            List<User> res = builder
                    .of(User.class, 2)
                    .andProvider(GptProvider.OPEN_AI)
                    .generate();
        });
    }
}
