package com.codinginfinity.benchmark.management.test.domain;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.service.utils.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.ZonedDateTime;
import java.util.Set;

import static com.codinginfinity.common.testing.EntityClassTestUtil.assertEntityClassWellDefined;
import static org.junit.Assert.assertEquals;

/**
 * Created by andrew on 2016/06/19.
 */
public class UserTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validateTestObject() {
        Set<ConstraintViolation<User>> violations = validator.validate(createUser());
        assertEquals(0, violations.size());
    }

    @Test
    public void usernameInvalidRegexPatternTest() {
        User user = createUser();
        user.setUsername("john#oe");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void usernameValidRegexPatternTest() {
        User user = createUser();
        user.setUsername("john_doe@example-domain.local");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void usernameTooShortTest() {
        User user = createUser();
        user.setUsername("");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void minUsernameLengthTest() {
        User user = createUser();
        user.setUsername("a");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void maxUsernameLengthTest() {
        User user = createUser();
        user.setUsername("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwx");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void usernameTooLongTest() {
        User user = createUser();
        user.setUsername("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void passwordTooShortTest() {
        User user = createUser();
        user.setPassword("1234567");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void passwordTooLongTest() {
        User user = createUser();
        user.setPassword("012345678901234567890123456789012345678901234567890123456789012345678901234567890");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void passwordNotNullTest() {
        User user = createUser();
        user.setPassword(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void firstNameTooLongTest() {
        User user = createUser();
        user.setFirstName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void firstNameNotNullTest() {
        User user = createUser();
        user.setFirstName(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void lastNameTooLongTest() {
        User user = createUser();
        user.setLastName("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void lastNameNotNullTest() {
        User user = createUser();
        user.setLastName(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void emailTooLongTest() {
        User user = createUser();
        user.setEmail("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz@abcdefghijklmnopqrstuvwxyz.abcdefghijklmnopqrstuvwxyz");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void emailNotNullTest() {
        User user = createUser();
        user.setEmail(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void invalidEmailTest() {
        User user = createUser();

        user.setEmail("john@doe@example.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());

        user.setEmail("john.doe.example.com");
        violations = validator.validate(user);
        assertEquals(1, violations.size());

        user.setEmail("john.doe@example..com");
        violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void validEmailTest() {
        User user = createUser();
        user.setEmail("john.doe@example.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void activatedisFalseTest() {
        User user = createUser();
        user.setActivated(false);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void activatedisTrueTest() {
        User user = createUser();
        user.setActivated(true);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void activationKeyIsNullTest() {
        User user = createUser();
        user.setActivationKey(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void activationKeyIsTooShortTest() {
        User user = createUser();
        user.setActivationKey("0123456789");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void activationKeyIsTooLongTest() {
        User user = createUser();
        user.setActivationKey("012345678901234567890");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void activationKeyLengthTest() {
        User user = createUser();
        user.setActivationKey(RandomUtils.generateActivationKey());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void resetKeyIsNullTest() {
        User user = createUser();
        user.setResetKey(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void resetKeyIsTooShortTest() {
        User user = createUser();
        user.setResetKey("0123456789");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void resetKeyIsTooLongTest() {
        User user = createUser();
        user.setResetKey("012345678901234567890");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void resetKeyLengthTest() {
        User user = createUser();
        user.setResetKey(RandomUtils.generateActivationKey());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void resetDateMustBePastTest() {
        User user = createUser();
        user.setResetDate(ZonedDateTime.now().minusMinutes(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void resetDateMustNotBeFutureTest() {
        User user = createUser();
        user.setResetDate(ZonedDateTime.now().plusDays(1));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void resetDateIsNullTest() {
        User user = createUser();
        user.setResetDate(null);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    private User createUser() {
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

    @Test
    public void wellDefinedEntityClass() {
        assertEntityClassWellDefined(User.class);
    }
}
