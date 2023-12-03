package com.recipes.api.common;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ControllerUtils {
    private ControllerUtils() {
    }

    /**
     * It checks if the object passed to it has any non-null fields
     *
     * @param obj The object to be validated.
     * @return A boolean value.
     */
    public static boolean isValidRequestBody(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        boolean isValid = Boolean.FALSE;
        for (Field field : fields) {
            field.setAccessible(Boolean.TRUE);
            if (field.get(obj) != null) {
                isValid = Boolean.TRUE;
            }
        }
        return isValid;
    }
}