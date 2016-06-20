package com.codinginfinity.benchmark.managenent.service.userManagement.response;

import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@Setter
@AllArgsConstructor
public class CompletePasswordResetResponse extends Response {

    private User user;
}