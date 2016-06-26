package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.response;

import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@NoArgsConstructor
public class GetAlgorithmCategoryResponse extends Response {

    private static final long serialVersionUID = 5380240516651985004L;

    private AlgorithmCategory category;
}
