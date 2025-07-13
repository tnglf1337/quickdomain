package util;

import com.quickdomain.util.ProviderReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProviderReaderTest {
    @Test
    @DisplayName("Ein Api-Endpoint eines Providers wird aus der Datei 'provider.yaml' geladen")
    void test1() {
        String provider = "gpt-provider";
        String expected = "someapi";

        String actual =  ProviderReader.loadApi(provider);

        assertThat(actual).isEqualTo(expected);
    }
}