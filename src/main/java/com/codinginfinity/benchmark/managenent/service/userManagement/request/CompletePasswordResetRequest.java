package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/15.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompletePasswordResetRequest extends Request {

    private static final long serialVersionUID = -5738648633676322178L;

    private String newPassword;

    private String key;
}
