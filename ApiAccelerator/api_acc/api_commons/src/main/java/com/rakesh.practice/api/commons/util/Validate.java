package com.rakesh.practice.api.commons.util;

import org.apache.commons.lang3.StringUtils;

public class Validate {
    public static void notEmpty(String in, String fieldName) {
        if (StringUtils.isNotBlank(in)) {
            throw new IllegalArgumentException(String.format("%s cannot be empty", fieldName));
        }
    }

    public static void notNull(Object in, String fieldName) {
        if (in == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", fieldName));
        }
    }
}
