package com.codinginfinity.benchmark.managenent.service.userManagement.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@AllArgsConstructor
public class GetUserWithAuthoritiesByIdRequest extends Request {

    private static final long serialVersionUID = -5519249225505372088L;

    private Long id;
}
