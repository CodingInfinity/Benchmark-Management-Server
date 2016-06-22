package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteUserRequest extends Request {

    private static final long serialVersionUID = 6894839043027328240L;

    private String username;
}
