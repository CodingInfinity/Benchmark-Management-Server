package com.codinginfinity.benchmark.management.service.userManagement.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangePasswordRequest extends Request {

    private static final long serialVersionUID = -1951412475710751444L;

    private String password;
}
