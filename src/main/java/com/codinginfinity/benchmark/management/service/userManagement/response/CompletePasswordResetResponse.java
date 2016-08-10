package com.codinginfinity.benchmark.management.service.userManagement.response;

import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.service.Response;
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
