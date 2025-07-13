package util;

import java.util.Objects;

public class FullDummyMixedEntity {
    private String name;
    private int age;
    private Double score;
    private boolean active;

    public FullDummyMixedEntity(String name, int age, Double score, boolean active) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullDummyMixedEntity that = (FullDummyMixedEntity) o;
        return age == that.age && active == that.active && Objects.equals(name, that.name) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, score, active);
    }
}
