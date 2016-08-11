package com.codinginfinity.benchmark.management.service.notification;

/**
 * Created by andrew on 2016/06/20.
 */

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.service.notification.exception.EmailNotSentException;
import com.codinginfinity.benchmark.management.service.notification.request.SendCreationEmailRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.subethamail.wiser.Wiser;
import org.thymeleaf.TemplateEngine;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.isNull;

/**
 * Created by andrew on 2016/06/21.
 */
public class SendCreationEmailTest extends AbstractTest {

    private Wiser wiser;

    @Value("${spring.mail.port}")
    private int port;

    @Inject
    @InjectMocks
    private Notification notification;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MessageSource messageSource;

    @Mock
    private TemplateEngine templateEngine;

    @Before
    public void setup() {
        wiser = new Wiser(port);
        wiser.start();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        wiser.stop();

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void sendCreationEmailTest() throws MessagingException, IOException, EmailNotSentException {

        doNothing().when(javaMailSender).send((MimeMessage)anyObject());
        when(javaMailSender.createMimeMessage()).thenReturn(new JavaMailSenderImpl().createMimeMessage());
        when(messageSource.getMessage(anyString(), isNull(Object[].class), any())).thenReturn("Activation Email");
        ArgumentCaptor<MimeMessage> argumentCaptor = ArgumentCaptor.forClass(MimeMessage.class);

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUsername("johndoe");
        user.setEmail("johndoe@example.com");
        user.setResetKey("0123456789");

        notification.sendCreationEmail(new SendCreationEmailRequest(user));

        /* Capture the message sent through the JavaMailSender service */
        verify(javaMailSender).send(argumentCaptor.capture());
        MimeMessage message = argumentCaptor.getValue();
        /* Assert that the message is as expected */
        assertEquals("Activation Email", message.getSubject());
        String content =  (String)message.getContent();
        assertTrue(content.contains("Benchmark Service Account Activation"));
        assertTrue(content.contains("Dear " + user.getUsername()));
        /* We are sending a plain text message, ensure message is not multipart by trying to cast content of message,
         * which should be a string to a Multipart class. Should fail with ClassCastException
         */
        thrown.expect(ClassCastException.class);
        ((Multipart)message.getContent()).getCount();
    }
}