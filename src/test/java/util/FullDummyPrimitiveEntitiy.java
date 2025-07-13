package util;

import java.util.Objects;

public class FullDummyPrimitiveEntitiy {
    private char charVal;
    private int intVal;
    private long longVal;
    private double doubleVal;
    private float floatVal;
    private short shortVal;
    private byte byteVal;
    private boolean boolVal;

    public FullDummyPrimitiveEntitiy(char charVal, int intVal, long longVal, double doubleVal,
                                     float floatVal, short shortVal, byte byteVal, boolean boolVal) {
        this.charVal = charVal;
        this.intVal = intVal;
        this.longVal = longVal;
        this.doubleVal = doubleVal;
        this.floatVal = floatVal;
        this.shortVal = shortVal;
        this.byteVal = byteVal;
        this.boolVal = boolVal;
    }

    // equals() und hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FullDummyPrimitiveEntitiy)) return false;
        FullDummyPrimitiveEntitiy that = (FullDummyPrimitiveEntitiy) o;
        return charVal == that.charVal &&
                intVal == that.intVal &&
                longVal == that.longVal &&
                Double.compare(that.doubleVal, doubleVal) == 0 &&
                Float.compare(that.floatVal, floatVal) == 0 &&
                shortVal == that.shortVal &&
                byteVal == that.byteVal &&
                boolVal == that.boolVal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(charVal, intVal, longVal, doubleVal, floatVal, shortVal, byteVal, boolVal);
    }

    @Override
    public String toString() {
        return "FullDummyPrimitveEntitiy{" +
                ", charVal=" + charVal +
                ", intVal=" + intVal +
                ", longVal=" + longVal +
                ", doubleVal=" + doubleVal +
                ", floatVal=" + floatVal +
                ", shortVal=" + shortVal +
                ", byteVal=" + byteVal +
                ", boolVal=" + boolVal +
                '}';
    }
}

