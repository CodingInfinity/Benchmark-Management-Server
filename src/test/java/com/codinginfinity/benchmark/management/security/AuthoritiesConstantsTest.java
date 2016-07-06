package com.codinginfinity.benchmark.management.security;

import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import com.codinginfinity.common.testing.TestingException;
import org.junit.Test;

import static com.codinginfinity.common.testing.ConstantClassTestUtil.assertConstantClassWellDefined;

/**
 * Created by andrew on 2016/07/06.
 */
public class AuthoritiesConstantsTest {

    @Test
    public void constantClassWellDefinedTest() throws TestingException {
        assertConstantClassWellDefined(AuthoritiesConstants.class);
    }
}
