package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@Setter
@AllArgsConstructor
public class CompletePasswordResetRequest extends Request {

    private String newPassword;
    private String key;
}
