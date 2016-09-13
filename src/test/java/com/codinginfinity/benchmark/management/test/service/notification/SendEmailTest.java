package com.codinginfinity.benchmark.management.test.service.notification;

import com.codinginfinity.benchmark.management.service.notification.Notification;
import com.codinginfinity.benchmark.management.service.notification.exception.EmailNotSentException;
import com.codinginfinity.benchmark.management.service.notification.request.SendEmailRequest;
import com.codinginfinity.benchmark.management.test.AbstractTest;
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
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
public class SendEmailTest extends AbstractTest {

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
    public void sendEmailTest() throws MessagingException, IOException, EmailNotSentException {

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

    @Test
    public void sendEmailFailedTest() throws MessagingException, IOException, EmailNotSentException {
        doThrow(new MailSendException("Test string")).when(javaMailSender).send((MimeMessage) anyObject());
        doThrow(new MailSendException("Test string")).when(javaMailSender).send((MimeMessage[]) anyObject());
        doThrow(new MailSendException("Test string")).when(javaMailSender).send((SimpleMailMessage) anyObject());
        doThrow(new MailSendException("Test string")).when(javaMailSender).send((SimpleMailMessage[]) anyObject());
        doThrow(new MailSendException("Test string")).when(javaMailSender).send((MimeMessagePreparator) anyObject());
        doThrow(new MailSendException("Test string")).when(javaMailSender).send((MimeMessagePreparator[]) anyObject());
        when(javaMailSender.createMimeMessage()).thenReturn(new JavaMailSenderImpl().createMimeMessage());
        thrown.expect(EmailNotSentException.class);

        notification.sendEmail(new SendEmailRequest("johndoe@example.com", "Unit Testing", "This is a test string", false, false));
    }

}
