package com.codinginfinity.benchmark.managenent.service.utils;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by andrew on 2016/06/15.
 */
public final class RandomUtils {

    private static final int STRING_LENGTH = 20;

    private RandomUtils() {
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(STRING_LENGTH);
    }

    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(STRING_LENGTH);
    }

    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(STRING_LENGTH);
    }
}
