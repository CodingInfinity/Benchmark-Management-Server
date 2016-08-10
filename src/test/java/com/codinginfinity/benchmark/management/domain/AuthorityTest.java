package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;
import static org.junit.Assert.assertEquals;

/**
 * Created by andrew on 2016/06/19.
 */
public class AuthorityTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validateTestObject() {
        Set<ConstraintViolation<Authority>> violations = validator.validate(new Authority(AuthoritiesConstants.ADMIN));
        assertEquals(0, violations.size());
    }

    @Test
    public void nameNotNullTest() {
        Set<ConstraintViolation<Authority>> violations = validator.validate(new Authority(null));
        assertEquals(1, violations.size());
    }

    @Test
    public void nameTooShortTest() {
        Set<ConstraintViolation<Authority>> violations = validator.validate(new Authority(""));
        assertEquals(1, violations.size());
    }

    @Test
    public void nameTooLongTest() {
        Set<ConstraintViolation<Authority>> violations = validator.validate(new Authority("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"));
        assertEquals(1, violations.size());
    }

    @Test
    public void nameMinTest() {
        Set<ConstraintViolation<Authority>> violations = validator.validate(new Authority("a"));
        assertEquals(0, violations.size());
    }

    @Test
    public void nameMaxTest() {
        Set<ConstraintViolation<Authority>> violations = validator.validate(new Authority("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx"));
        assertEquals(0, violations.size());
    }

    @Test
    public void nameTest() {
        Set<ConstraintViolation<Authority>> violations = validator.validate(new Authority("ROLE_ADMIN"));
        assertEquals(0, violations.size());
    }

    @Test
    public void wellDefinedEntityClass() {
        assertEntityClassWellDefined(Authority.class);
    }

}

