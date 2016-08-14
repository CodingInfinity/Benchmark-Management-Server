package com.codinginfinity.benchmark.management.test.domain;

import com.codinginfinity.benchmark.management.domain.Category;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by andrew on 2016/06/25.
 */
public abstract class AbstractCategoryTest<T extends Category> {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nameNotNullTest() {
        T category = getCategory();
        category.setName(null);
        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        assertEquals(1, violations.size());
    }

    @Test
    public void nameTooLongTest() {
        T category = getCategory();
        category.setName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz");
        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        assertEquals(1, violations.size());
    }

    protected abstract <T extends Category> T getCategory();

}

