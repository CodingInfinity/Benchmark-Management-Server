package com.codinginfinity.benchmark.management.service.notification;

import com.codinginfinity.benchmark.management.config.BenchmarkProperties;
import com.codinginfinity.benchmark.management.service.notification.exception.EmailNotSentException;
import com.codinginfinity.benchmark.management.service.notification.request.SendActivationEmailRequest;
import com.codinginfinity.benchmark.management.service.notification.request.SendCreationEmailRequest;
import com.codinginfinity.benchmark.management.service.notification.request.SendEmailRequest;
import com.codinginfinity.benchmark.management.service.notification.request.SendPasswordResetMailRequest;
import com.codinginfinity.benchmark.management.service.notification.response.SendActivationEmailResponse;
import com.codinginfinity.benchmark.management.service.notification.response.SendCreationEmailResponse;
import com.codinginfinity.benchmark.management.service.notification.response.SendEmailResponse;
import com.codinginfinity.benchmark.management.service.notification.response.SendPasswordResetMailResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.CharEncoding;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * A reference implementation of the {@link Notification} service contract.
 *
 * @see com.codinginfinity.benchmark.management.service.notification.exception
 * @see com.codinginfinity.benchmark.management.service.notification.request
 * @see com.codinginfinity.benchmark.management.service.notification.response
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */
@Service
@Slf4j
public class NotificationImpl implements Notification {

    private static final String USER = "user";
    private static final String BASE_URL = "baseUrl";

    @Inject
    private JavaMailSender javaMailSender;

    @Inject
    private MessageSource messageSource;

    @Inject
    private SpringTemplateEngine templateEngine;

    @Inject
    private BenchmarkProperties properties;

    @Override
    public SendEmailResponse sendEmail(SendEmailRequest request) throws EmailNotSentException {
        log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                request.isMultipart(), request.isHtml(), request.getTo(), request.getSubject(), request.getContent());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, request.isMultipart(), CharEncoding.UTF_8);
            message.setTo(request.getTo());
            message.setFrom(properties.getMail().getFrom());
            message.setSubject(request.getSubject());
            message.setText(request.getContent(), request.isHtml());
            javaMailSender.send(mimeMessage);
            log.debug("Sent e-mail to User '{}'", request.getTo());
        } catch (Exception e) {
            log.warn("E-mail could not be sent to user '{}', exception is: {}", request.getTo(), e.getMessage());
            throw new EmailNotSentException(e.getMessage());
        }
        return new SendEmailResponse();
    }

    @Override
    public SendActivationEmailResponse sendActivationEmail(SendActivationEmailRequest request) throws EmailNotSentException {
        log.debug("Sending activation e-mail to '{}'", request.getUser().getEmail());
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable(USER, request.getUser());
        context.setVariable(BASE_URL, properties.getFrontend().getBaseUrl());
        String content = templateEngine.process("activationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(new SendEmailRequest(request.getUser().getEmail(), subject, content, false, true));
        return new SendActivationEmailResponse();
    }

    @Override
    public SendCreationEmailResponse sendCreationEmail(SendCreationEmailRequest request) throws EmailNotSentException {
        log.debug("Sending creation e-mail to '{}'", request.getUser().getEmail());
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable(USER, request.getUser());
        context.setVariable(BASE_URL, properties.getFrontend().getBaseUrl());
        String content = templateEngine.process("creationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(new SendEmailRequest(request.getUser().getEmail(), subject, content, false, true));
        return new SendCreationEmailResponse();
    }

    @Override
    public SendPasswordResetMailResponse sendPasswordResetMail(SendPasswordResetMailRequest request) throws EmailNotSentException {
        log.debug("Sending password reset e-mail to '{}'", request.getUser().getEmail());
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable(USER, request.getUser());
        context.setVariable(BASE_URL, properties.getFrontend().getBaseUrl());
        String content = templateEngine.process("passwordResetEmail", context);
        String subject = messageSource.getMessage("email.reset.title", null, locale);
        sendEmail(new SendEmailRequest(request.getUser().getEmail(), subject, content, false, true));
        return new SendPasswordResetMailResponse();
    }
}
