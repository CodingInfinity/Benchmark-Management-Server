package com.codinginfinity.benchmark.managenent.service.notification;

import com.codinginfinity.benchmark.managenent.service.notification.exception.EmailNotSentException;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendActivationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendCreationEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendEmailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.request.SendPasswordResetMailRequest;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendActivationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendCreationEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendEmailResponse;
import com.codinginfinity.benchmark.managenent.service.notification.response.SendPasswordResetMailResponse;

/**
 * Defines the service contract for the notification module, including all request, response and pre-conditions.
 * Important to note that all pre-coditions are mapped onto exception objects.
 *
 * A reference implementation is provided in the {@link NotificationImpl} class.
 *
 * @see com.codinginfinity.benchmark.managenent.service.notification.exception
 * @see com.codinginfinity.benchmark.managenent.service.notification.request
 * @see com.codinginfinity.benchmark.managenent.service.notification.response
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */
public interface Notification {

    /**
     * Used to send an email from the system to a specified address.
     * @param request The request encapsulated as an {@link SendEmailRequest} object.
     * @return Returns the result in an encapsulated {@link SendEmailResponse} object.
     * @throws EmailNotSentException Thrown when the system could not sent the email in question.
     * @since 1.0.0
     */
    SendEmailResponse sendEmail(SendEmailRequest request) throws EmailNotSentException;

    /**
     * Used to send a newly created managed user an email regarding the settings of their password.
     * @param request The request encapsulated as an {@link SendActivationEmailRequest} object.
     * @return Returns the result in an encapsulated {@link SendActivationEmailResponse} object.
     * @throws EmailNotSentException Thrown when the system could not sent the email in question.
     * @since 1.0.0
     */
    SendActivationEmailResponse sendActivationEmail(SendActivationEmailRequest request) throws EmailNotSentException;

    /**
     * Used to send a newly created unmanaged user an email regarding the activation of their account.
     * @param request The request encapsulated as an {@link SendCreationEmailRequest} object.
     * @return Returns the result in an encapsulated {@link SendCreationEmailResponse} object.
     * @throws EmailNotSentException Thrown when the system could not sent the email in question.
     * @since 1.0.0
     */
    SendCreationEmailResponse sendCreationEmail(SendCreationEmailRequest request) throws EmailNotSentException;

    /**
     * Used to send a user a password with there associated link and token to reset their password on there account.
     * @param request The request encapsulated as an {@link SendPasswordResetMailRequest} object.
     * @return Returns the result in an encapsulated {@link SendPasswordResetMailResponse} object.
     * @throws EmailNotSentException Thrown when the system could not sent the email in question.
     * @since 1.0.0
     */
    SendPasswordResetMailResponse sendPasswordResetMail(SendPasswordResetMailRequest request) throws EmailNotSentException;
}
