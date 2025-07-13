package com.quickdomain.core.domain;

public class ParameterCaster {
    public static Object castValue(String s, Class<?> type) {
        if (type == String.class) {
            return s;
        } else if (type == Integer.class || type == int.class) {
            return Integer.parseInt(s);
        } else if (type == Long.class || type == long.class) {
            return Long.parseLong(s);
        } else if (type == Double.class || type == double.class) {
            return Double.parseDouble(s);
        } else if (type == Float.class || type == float.class) {
            return Float.parseFloat(s);
        } else if (type == Boolean.class || type == boolean.class) {
            return Boolean.parseBoolean(s);
        } else if (type == Character.class || type == char.class) {
            if (s.length() != 1) {
                throw new IllegalArgumentException("Cannot convert to char: " + s);
            }
            return s.charAt(0);
        } else if (type == Short.class || type == short.class) {
            System.out.println("parsing shrt");
            return Short.parseShort(s);
        } else if (type == Byte.class || type == byte.class) {
            return Byte.parseByte(s);
        }
        throw new IllegalArgumentException("Unsupported type: " + type.getName());
    }

}
