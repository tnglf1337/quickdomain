package util;

import java.util.List;

public class EntityMother {

    public static List<FullDummyWrapperEntity> getFullDummyWrapperEntities() {
        return List.of(
                new FullDummyWrapperEntity(
                        "Hallo Welt",
                        'A',
                        42,
                        1234567890L,
                        3.1415,
                        2.718f,
                        (short) 1000,
                        (byte) 12
                ),
                new FullDummyWrapperEntity(
                        "Hello World",
                        'B',
                        84,
                        987654321L,
                        2.71828,
                        3.1415f,
                        (short) 2000,
                        (byte) 34
                )
        );
    }

    public static List<FullDummyMixedEntity> getFullDummyMixedEntities() {
        return List.of(
                new FullDummyMixedEntity("Alice", 30, 95.5, true),
                new FullDummyMixedEntity("Bob", 25, 82.0, false)
        );
    }

    public static List<FullDummyPrimitiveEntitiy> getFullDummyPrimitiveEntities() {
        return List.of(
                new FullDummyPrimitiveEntitiy(
                        'A',
                        42,
                        1234567890L,
                        3.1415,
                        2.718f,
                        (short) 1000,
                        (byte) 12,
                        true
                ),
                new FullDummyPrimitiveEntitiy(
                        'B',
                        84,
                        9876543210L,
                        1.618,
                        1.4142f,
                        (short) 2000,
                        (byte) 34,
                        false
                )
        );
    }
}
