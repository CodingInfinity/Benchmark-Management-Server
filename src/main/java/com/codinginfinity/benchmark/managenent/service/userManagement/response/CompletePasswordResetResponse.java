package com.codinginfinity.benchmark.managenent.service.userManagement.response;

import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@AllArgsConstructor
public class CompletePasswordResetResponse extends Response {

    private static final long serialVersionUID = -8437866694889215577L;

    private User user;
}
