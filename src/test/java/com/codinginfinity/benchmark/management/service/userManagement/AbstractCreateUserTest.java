package com.codinginfinity.benchmark.management.service.userManagement;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.AuthorityRepository;
import com.codinginfinity.benchmark.managenent.repository.UserRepository;
import com.codinginfinity.benchmark.managenent.service.notification.Notification;
import com.codinginfinity.benchmark.managenent.service.notification.exception.EMailNotSentException;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.exception.DuplicateUsernameException;
import com.codinginfinity.benchmark.managenent.service.userManagement.exception.EmailAlreadyExistsException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;
import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Created by andrew on 2016/06/25.
 */
public abstract class AbstractCreateUserTest extends AbstractTest {

    protected UserRepository userRepository;

    @Mock
    protected AuthorityRepository authorityRepository;

    @Mock
    protected PasswordEncoder passwordEncoder;

    @Mock
    protected Notification notification;

    @InjectMocks
    @Inject
    protected UserManagement userManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void duplicateUsernameTest()
            throws DuplicateUsernameException, EmailAlreadyExistsException, EMailNotSentException {
        User user = new User();
        user.setUsername("johndoe");

        when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.of(user));
        thrown.expect(DuplicateUsernameException.class);
        thrown.expectMessage("Username already exists");

        duplicateUsername("johndoe","p@ssw0rd","John", "Doe", "johndoe@exampe.com");
    }

    @Test
    public void duplicateEmailAddressTest()
            throws DuplicateUsernameException, EmailAlreadyExistsException, EMailNotSentException {
        User user = new User();
        user.setUsername("johndoe");
        user.setEmail("johndoe@exampe.com");

        when(userRepository.findOneByUsername("johndoe")).thenReturn(Optional.empty());
        when(userRepository.findOneByEmail("johndoe@exampe.com")).thenReturn(Optional.of(user));
        thrown.expect(EmailAlreadyExistsException.class);
        thrown.expectMessage("Email already exists");

        duplicateEmailAddress("johndoe","p@ssw0rd","John", "Doe", "johndoe@exampe.com");
    }

    public abstract void duplicateUsername(String username, String password, String firstName, String lastName, String email) throws DuplicateUsernameException, EmailAlreadyExistsException, EMailNotSentException;

    public abstract void duplicateEmailAddress(String username, String password, String firstName, String lastName, String email) throws DuplicateUsernameException, EmailAlreadyExistsException, EMailNotSentException;
}
