package com.codinginfinity.benchmark.management.service.utils;

import com.codinginfinity.benchmark.managenent.service.utils.RandomUtils;
import org.apache.commons.lang.StringUtils;
import org.hsqldb.lib.StringUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by andrew on 2016/06/19.
 */
public class RandomUtilsTest {

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
