package domain;

import com.quickdomain.core.domain.ParameterCaster;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterCasterTest {
    static Stream<Arguments> provideTypesAndValues() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of("hello", String.class, "hello"),
                org.junit.jupiter.params.provider.Arguments.of("42", Integer.class, 42),
                org.junit.jupiter.params.provider.Arguments.of("42", int.class, 42),
                org.junit.jupiter.params.provider.Arguments.of("1234567890", Long.class, 1234567890L),
                org.junit.jupiter.params.provider.Arguments.of("1234567890", long.class, 1234567890L),
                org.junit.jupiter.params.provider.Arguments.of("3.14", Double.class, 3.14),
                org.junit.jupiter.params.provider.Arguments.of("3.14", double.class, 3.14),
                org.junit.jupiter.params.provider.Arguments.of("2.71", Float.class, 2.71f),
                org.junit.jupiter.params.provider.Arguments.of("2.71", float.class, 2.71f),
                org.junit.jupiter.params.provider.Arguments.of("true", Boolean.class, true),
                org.junit.jupiter.params.provider.Arguments.of("true", boolean.class, true),
                org.junit.jupiter.params.provider.Arguments.of("c", Character.class, 'c'),
                org.junit.jupiter.params.provider.Arguments.of("c", char.class, 'c'),
                org.junit.jupiter.params.provider.Arguments.of("120", Byte.class, (byte) 120),
                org.junit.jupiter.params.provider.Arguments.of("120", byte.class, (byte) 120),
                org.junit.jupiter.params.provider.Arguments.of("32000", Short.class, (short) 32000),
                org.junit.jupiter.params.provider.Arguments.of("32000", short.class, (short) 32000)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTypesAndValues")
    @DisplayName("castValue wirft keine Exception und liefert den richtigen Wert")
    void testCastValue(String input, Class<?> type, Object expected) {
        Object result = ParameterCaster.castValue(input, type);
        assertEquals(expected, result);
        assertEquals(type.isPrimitive() ? wrap(type) : type, result.getClass());
    }

    private static Class<?> wrap(Class<?> type) {
        if (type == int.class) return Integer.class;
        if (type == long.class) return Long.class;
        if (type == double.class) return Double.class;
        if (type == float.class) return Float.class;
        if (type == boolean.class) return Boolean.class;
        if (type == char.class) return Character.class;
        if (type == byte.class) return Byte.class;
        if (type == short.class) return Short.class;
        return type;
    }
}