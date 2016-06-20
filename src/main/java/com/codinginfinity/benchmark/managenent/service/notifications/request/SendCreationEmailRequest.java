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
public class SendCreationEmailRequest extends Request {

    private static final long serialVersionUID = 8954603250365346769L;

    private User user;
}
