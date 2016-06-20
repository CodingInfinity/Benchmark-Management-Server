package com.codinginfinity.benchmark.managenent.service.notifications.request;

import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@AllArgsConstructor
public class SendPasswordResetMailRequest extends Request {

    private static final long serialVersionUID = -2724103231721216098L;

    private User user;
}
