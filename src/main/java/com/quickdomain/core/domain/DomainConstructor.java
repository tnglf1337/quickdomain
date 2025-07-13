package com.quickdomain.core.domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DomainConstructor<T> {

    private final Class<T> domainClass;
    private final int numberOfEntities;
    private final Map<String, List<String>> contentMap;

    public DomainConstructor(Class<T> domainClass, int numberOfEntities, Map<String, List<String>> contentMap) {
        this.domainClass = domainClass;
        this.numberOfEntities = numberOfEntities;
        this.contentMap = contentMap;
    }

    public List<T> constructSimple() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<T> domainEntities = new ArrayList<>();
        Class<?>[] paramTypes = getDomainFieldTypes();
        for (int i = 0; i < numberOfEntities; i++) {
            List<Object> constructorArgs = new ArrayList<>();

            int j = 0;
            for (List<String> valueList : contentMap.values()) {
                Class<?> currentFieldType = paramTypes[j];
                Object castedValue = ParameterCaster.castValue(valueList.get(i), currentFieldType);
                constructorArgs.add(castedValue);
                j++;
            }

            Constructor<T> constructor = domainClass.getConstructor(paramTypes);
            T entity = constructor.newInstance(constructorArgs.toArray());
            domainEntities.add(entity);
        }
        return domainEntities;
    }

    public Class<?>[] getDomainFieldTypes() {
        Class<?>[] types = new Class<?>[domainClass.getDeclaredFields().length];

        Field[] domainFields = domainClass.getDeclaredFields();

        for (int i = 0; i < domainFields.length; i++) {
            types[i] = domainFields[i].getType();
        }

        return types;
    }
}
