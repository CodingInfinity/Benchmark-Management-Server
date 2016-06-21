package com.codinginfinity.benchmark.managenent.service.notification;

import com.codinginfinity.benchmark.managenent.service.notification.request.SendActivationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendCreationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendPasswordResetMailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendActivationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendCreationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendPasswordResetMailResponse;

import javax.mail.MessagingException;

/**
 * Created by andrew on 2016/06/20.
 */
public interface Notification {

    SendEmailResponse sendEmail(SendEmailRequest request) throws MessagingException;
    SendActivationEmailResponse sendActivationEmail(SendActivationEmailRequest request);
    SendCreationEmailResponse sendCreationEmail(SendCreationEmailRequest request);
    SendPasswordResetMailResponse sendPasswordResetMail(SendPasswordResetMailRequest request);
}
