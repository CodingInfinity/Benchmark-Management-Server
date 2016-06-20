package com.codinginfinity.benchmark.managenent.service.notifications;

import com.codinginfinity.benchmark.managenent.service.notifications.request.SendActivationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notifications.request.SendCreationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notifications.request.SendEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notifications.request.SendPasswordResetMailRequest;
import com.codinginfinity.benchmark.managenent.service.notifications.response.SendActivationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notifications.response.SendCreationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notifications.response.SendEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notifications.response.SendPasswordResetMailResponse;

/**
 * Created by andrew on 2016/06/20.
 */
public interface Notification {

    SendEmailResponse sendEmail(SendEmailRequest request);
    SendActivationEmailResponse sendActivationEmail(SendActivationEmailRequest request);
    SendCreationEmailResponse sendCreationEmail(SendCreationEmailRequest request);
    SendPasswordResetMailResponse sendPasswordResetMail(SendPasswordResetMailRequest request);
}
