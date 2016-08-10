package com.codinginfinity.benchmark.management.service.utils;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import static net.trajano.commons.testing.UtilityClassTestUtil.assertUtilityClassWellDefined;
import static org.junit.Assert.*;

/**
 * Created by andrew on 2016/06/19.
 */
public class RandomUtilsTest {

    @Test
    public void utilityClassWellDefinedTest() {
        assertUtilityClassWellDefined(RandomUtils.class);
    }

    @Test
    public void generatePasswordTest() {
        testString(RandomUtils.generateActivationKey());
    }

    @Test
    public void generateActivationKeyTest() {
        testString(RandomUtils.generateActivationKey());
    }

    @Test
    public void generateResetKeyTest() {
        testString(RandomUtils.generateResetKey());
    }

    private void testString(String string) {
        assertEquals(RandomUtils.getStringLength(), string.length());
        assertTrue(StringUtils.isAlphanumeric(string));
        assertFalse(StringUtils.isAlpha(string));
        assertTrue(StringUtils.isNumeric(string));
        assertTrue(StringUtils.isAsciiPrintable(string));
        assertTrue(StringUtils.isNotBlank(string));
        assertTrue(StringUtils.isNotEmpty(string));
    }
}
