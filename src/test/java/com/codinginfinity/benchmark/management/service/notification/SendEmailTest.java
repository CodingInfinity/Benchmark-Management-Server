package com.codinginfinity.benchmark.management.service.notification;

import com.codinginfinity.benchmark.managenent.ManagementApp;
import com.codinginfinity.benchmark.managenent.service.notification.Notification;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendEmailRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.subethamail.wiser.Wiser;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * Created by andrew on 2016/06/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ManagementApp.class)
public class SendEmailTest {

    private Wiser wiser;

    @Value("${spring.mail.port}")
    private int port;

    @Inject
    @InjectMocks
    private Notification notification;

    @Mock
    private JavaMailSender javaMailSender;

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
    public void sendEmailTest() throws MessagingException, IOException {

        doNothing().when(javaMailSender).send((MimeMessage)anyObject());
        when(javaMailSender.createMimeMessage()).thenReturn(new JavaMailSenderImpl().createMimeMessage());

        ArgumentCaptor<MimeMessage> argumentCaptor = ArgumentCaptor.forClass(MimeMessage.class);

        notification.sendEmail(new SendEmailRequest("johndoe@example.com", "Unit Testing", "This is a test string", false, false));

        /* Capture the message sent through the JavaMailSender service */
        verify(javaMailSender).send(argumentCaptor.capture());
        MimeMessage message = argumentCaptor.getValue();
        /* Assert that the message is as expected */
        assertEquals("Unit Testing", message.getSubject());
        assertEquals("This is a test string", (String)message.getContent());
        /* We are sending a plain text message, ensure message is not multipart by trying to cast content of message,
         * which should be a string to a Multipart class. Should fail with ClassCastException
         */
        thrown.expect(ClassCastException.class);
        ((Multipart)message.getContent()).getCount();

    }


}
