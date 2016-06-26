package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.response;

import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@AllArgsConstructor
public class GetAlgorithmByIdResponse extends Response {

    private static final long serialVersionUID = -6185910613063888087L;

    Algorithm algorithm;
}
