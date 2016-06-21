package com.codinginfinity.benchmark.managenent.service.userManagement.response;

import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * Created by andrew on 2016/06/21.
 */
@Getter
@AllArgsConstructor
public class CreateUnmanagedUserResponse extends Response{

    private static final long serialVersionUID = 8599107702364880528L;

    private User user;
}
