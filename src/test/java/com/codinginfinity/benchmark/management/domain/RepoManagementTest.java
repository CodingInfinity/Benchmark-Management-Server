package com.codinginfinity.benchmark.management.domain;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by reinhardt on 2016/06/26.
 */
public abstract class RepoManagementTest <T extends RepoEntity, S extends JpaRepository<T, Long>, V extends Category>{

    protected static Validator validator;

    @BeforeClass
    public static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validateTestObject() {
        Set<ConstraintViolation<T>> violations = validator.validate(createObject());
        assertEquals(0, violations.size());
    }

    @Test
    public void nameNotNullTest(){
        T object = createObject();
        object.setName(null);
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        assertEquals(1, violations.size());
    }

    @Test
    public void nameTooLongTest() {
        T object = createObject();
        object.setName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        assertEquals(1, violations.size());
    }

    @Test
    public void userNotNullTest(){
        T object = createObject();
        object.setUser(null);
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        assertEquals(1, violations.size());
    }

    abstract T makeObjectOfType();
    abstract void setCategoriesForType(T obj);

    private T createObject() {
        T object = makeObjectOfType();
        object.setUser(createUser());
        object.setName("Dirty Sort");
        setCategoriesForType(object);
        return object;
    }

    protected User createUser() {
        User result = new User();
        result.setUsername("johndoe");
        result.setPassword("p@$$w0rd");
        result.setFirstName("John");
        result.setLastName("Doe");
        result.setEmail("johndoe@example.com");
        result.setActivated(true);
        result.setActivationKey(null);
        result.setResetDate(null);
        result.setResetKey(null);
        return result;
    }
}
