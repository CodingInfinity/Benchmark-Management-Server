package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@AllArgsConstructor
public class ChangePasswordRequest extends Request {

    private static final long serialVersionUID = -1951412475710751444L;

    private String password;
}
