package util;

import java.util.Objects;

public class FullDummyWrapperEntity {
    private String str;
    private Character charVal;
    private Integer intVal;
    private Long longVal;
    private Double doubleVal;
    private Float floatVal;
    private Short shortVal;
    private Byte byteVal;

    public FullDummyWrapperEntity(String str, Character charVal, Integer intVal, Long longVal, Double doubleVal, Float floatVal, Short shortVal, Byte byteVal) {
        this.str = str;
        this.charVal = charVal;
        this.intVal = intVal;
        this.longVal = longVal;
        this.doubleVal = doubleVal;
        this.floatVal = floatVal;
        this.shortVal = shortVal;
        this.byteVal = byteVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullDummyWrapperEntity that = (FullDummyWrapperEntity) o;
        return Objects.equals(str, that.str) && Objects.equals(charVal, that.charVal) && Objects.equals(intVal, that.intVal) && Objects.equals(longVal, that.longVal) && Objects.equals(doubleVal, that.doubleVal) && Objects.equals(floatVal, that.floatVal) && Objects.equals(shortVal, that.shortVal) && Objects.equals(byteVal, that.byteVal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(str, charVal, intVal, longVal, doubleVal, floatVal, shortVal, byteVal);
    }
}
