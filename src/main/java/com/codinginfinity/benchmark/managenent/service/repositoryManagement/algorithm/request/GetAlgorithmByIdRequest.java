package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@AllArgsConstructor
public class GetAlgorithmByIdRequest extends Request {

    private static final long serialVersionUID = -1289182829601186950L;

    Long id;
}
