package com.codinginfinity.benchmark.management.service.notification.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/20.
 */
@Getter
@AllArgsConstructor
public class SendEmailRequest extends Request {

    private static final long serialVersionUID = 4630755038823667076L;

    private String to;

    private String subject;

    private String content;

    private boolean isMultipart;

    private boolean isHtml;
}
