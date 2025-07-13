package domain;

import com.quickdomain.core.domain.DomainValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import util.Person;
import util.User;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DomainValidatorTest {

    @Nested
    class ValidDomains {
        @DisplayName("Eine Domain, die nur primitive Felder besitzt und numberOfEntities liegt in [0,9999], löst beim Validieren keine Exception aus")
        @Test
        void simpleDomainValidation1() {
            assertDoesNotThrow(() ->  DomainValidator.validateSimpleDomain(User.class, 10));
        }
    }

    @Nested
    class InvalidDomains {
        @DisplayName("Wenn numberOfEntities außerhalb von [0,9999] liegt, wird eine Exception geworfen")
        @ParameterizedTest
        @CsvSource(value = "-1,10000")
        void simpleDomainInvalidation1(int n) {
            assertThrows(RuntimeException.class, () -> DomainValidator.validateSimpleDomain(User.class, n));
        }

        @DisplayName("Eine Domain, die mindestens einen Referenztyp-Feld besitzt, löst beim Validieren eine Exception aus")
        @Test
        void multiDomainInvalidation2() {
            assertThrows(RuntimeException.class, () -> DomainValidator.validateSimpleDomain(Person.class, 3));
        }
    }

}