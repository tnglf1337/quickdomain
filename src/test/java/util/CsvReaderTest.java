package util;

import com.quickdomain.util.CsvReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CsvReaderTest {

    @Test
    @DisplayName("Eine Csv-Datei mit Inhalt im korrekten Format wird erfolgreich")
    void test1() {
        Path filePath = Path.of("src/test/resources/dummy-content.csv");
        String expected = "username,user1,user2\nemail,user1@example.com,user2@example.com\nage,20,30";

        String actual = CsvReader.readCsvContent(filePath);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Eine leere Csv-Datei wirft beim einlesen eine Exception")
    void test2() {
        Path filePath = Path.of("src/test/resources/dummy-empty.csv");
        assertThrows(IllegalArgumentException.class, () -> CsvReader.readCsvContent(filePath));
    }

    @Test
    @DisplayName("Eine nicht existierende Csv-Datei wirft beim einlesen eine Exception")
    void test3() {
        Path filePath = Path.of("src/test/resources/huh.csv");
        assertThrows(UncheckedIOException.class, () -> CsvReader.readCsvContent(filePath));
    }
}
