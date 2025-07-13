package com.quickdomain.core.domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleDomainConstructor<T> {

    private final Class<T> domainClass;
    private final int numberOfEntities;
    private final Map<String, List<String>> contentMap;

    public SimpleDomainConstructor(Class<T> domainClass, int numberOfEntities, Map<String, List<String>> contentMap) {
        this.domainClass = domainClass;
        this.numberOfEntities = numberOfEntities;
        this.contentMap = contentMap;
    }

    public List<T> constructSimple(){
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

            try {
                Constructor<T> constructor = domainClass.getConstructor(paramTypes);
                T entity = constructor.newInstance(constructorArgs.toArray());
                domainEntities.add(entity);
            } catch (NoSuchMethodException e) {
                System.out.println("No matching constructor not found for class: " + domainClass.getName());
                System.out.println(e.getMessage());
            } catch (IllegalAccessException e) {
                System.out.println("Illegal access while trying to instantiate class: " + domainClass.getName());
                System.out.println(e.getMessage());
            } catch (InstantiationException e) {
                System.out.println("Failed to instantiate class: " + domainClass.getName());
                System.out.println(e.getMessage());
            } catch (InvocationTargetException e) {
                System.out.println("Invocation target exception while trying to instantiate class: " + domainClass.getName());
                System.out.println(e.getMessage());
            }
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
