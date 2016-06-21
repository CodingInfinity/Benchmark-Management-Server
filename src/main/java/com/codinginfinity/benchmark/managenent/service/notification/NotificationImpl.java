package com.codinginfinity.benchmark.managenent.service.notification;

import com.codinginfinity.benchmark.managenent.service.notification.request.SendActivationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendCreationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendPasswordResetMailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendActivationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendCreationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendPasswordResetMailResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.CharEncoding;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

/**
 * Created by andrew on 2016/06/20.
 */
@Service
@Slf4j
public class NotificationImpl implements Notification {

    private static final String USER = "user";
    private static final String BASE_URL = "baseUrl";
    // ToDo: Extract below property to Spring application yaml file
    private static final String WEB_BASE_URL = "localhost:8080";

    @Inject
    private JavaMailSender javaMailSender;

    @Inject
    private MessageSource messageSource;

    @Inject
    private SpringTemplateEngine templateEngine;

    @Override
    public SendEmailResponse sendEmail(SendEmailRequest request) {
        log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                request.isMultipart(), request.isHtml(), request.getTo(), request.getSubject(), request.getContent());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, request.isMultipart(), CharEncoding.UTF_8);
            message.setTo(request.getTo());
            // ToDo: Extract to external properties file
            message.setFrom("benchmarkservice@cs.up.ac.za");
            message.setSubject(request.getSubject());
            message.setText(request.getContent(), request.isHtml());
            javaMailSender.send(mimeMessage);
            log.debug("Sent e-mail to User '{}'", request.getTo());
        } catch (Exception e) {
            log.warn("E-mail could not be sent to user '{}', exception is: {}", request.getTo(), e.getMessage());
        }
        return new SendEmailResponse();
    }

    @Override
    public SendActivationEmailResponse sendActivationEmail(SendActivationEmailRequest request) {
       return null;
    }

    @Override
    public SendCreationEmailResponse sendCreationEmail(SendCreationEmailRequest request) {
        return null;
    }

    @Override
    public SendPasswordResetMailResponse sendPasswordResetMail(SendPasswordResetMailRequest request) {
        return null;
    }
}
