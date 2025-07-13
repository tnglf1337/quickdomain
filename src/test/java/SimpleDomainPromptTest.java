import com.quickdomain.exception.UnsupportedLanguageException;
import com.quickdomain.prompt.SimpleDomainPrompt;
import util.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleDomainPromptTest {

    @DisplayName("Eine deutsche Prompt für eine User-Entity wird erfolgreich gebaut")
    @Test
    public void buildGermanUserEntityPrompt() {
        Field[] classFields = User.class.getDeclaredFields();
        Locale locale = Locale.GERMANY;
        int nDomains = 5;

        String prompt = new SimpleDomainPrompt()
                .withMeta(classFields, locale, nDomains)
                .withCondition("some condition")
                .build();

        assertThat(prompt).isEqualTo(
                """
                        Generiere n=5 zufällige Werte für folgende m=3 Variablen:
                        - 'username'
                        - 'email'
                        - 'age'
                        Das Format der Ausgabe muss folgendermaßen sein:
                        - Für die Variable i<=m: 'variable,wert1,wert2,wert3,wert4' und so weiter bis n.
                        - Achte darauf, dass die generierten Werte keine Duplikate sind.
                        - some condition
                        Generiere auf keinen Fall anderen Output, außer wenn ein Fehler bei dir passiert: -1"""
        );
    }

    @DisplayName("Wenn eine unsupported Locale verwendet wird, wird eine Exception geworfen.")
    @Test
    public void unsupportedLocaleForPrompt() {
        Field[] classFields = User.class.getDeclaredFields();
        Locale locale = Locale.CHINA;
        int nDomains = 5;

        assertThrows(UnsupportedLanguageException.class, () -> {
            String prompt = new SimpleDomainPrompt()
                    .withMeta(classFields, locale, nDomains)
                    .withCondition("some condition")
                    .build();
        });
    }
}
