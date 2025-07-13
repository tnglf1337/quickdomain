package com.quickdomain.core.domain;


import java.lang.reflect.Field;
import java.util.Set;

public class DomainValidator {

    private static final Set<Class<?>> ALLOWED_SIMPLE_TYPES = Set.of(
            String.class,
            Integer.class,
            Double.class,
            Float.class,
            Character.class,
            Long.class,
            Short.class,
            Byte.class,
            Boolean.class,
            int.class,
            double.class,
            float.class,
            char.class,
            long.class,
            short.class,
            byte.class,
            boolean.class
    );

    public static void validateSimpleDomain(Class<?> domain, Integer n) {
        if (n < 0 || n > 9999) throw new RuntimeException();

        if(domain == null){
            throw new RuntimeException("domain is null");
        }

        Field[] domainField = domain.getDeclaredFields();

        Class<?>[] fieldTypes = new Class<?>[domainField.length];

        for (int i = 0; i < domainField.length; i++) {
            fieldTypes[i] = domainField[i].getType();
            System.out.println(domainField[i].getType());
            if (!ALLOWED_SIMPLE_TYPES.contains(fieldTypes[i])) throw new RuntimeException();
        }
    }
}
