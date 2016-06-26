package com.codinginfinity.benchmark.management.domain;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.domain.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;
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
    public void nameNotNullTest() {
        T object = createObject();
        object.setName(null);
        //TODO: a compilation error is generated : "object" is not a valid parameter to the validate function
        /*
        Set<ConstraintViolation<Category>> violations = validator.validate(object);
        assertEquals(1, violations.size());
        */
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
