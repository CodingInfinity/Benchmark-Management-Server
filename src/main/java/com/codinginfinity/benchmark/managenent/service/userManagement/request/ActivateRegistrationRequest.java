package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@Setter
@AllArgsConstructor
public class ActivateRegistrationRequest extends Request {

    private String key;
}
