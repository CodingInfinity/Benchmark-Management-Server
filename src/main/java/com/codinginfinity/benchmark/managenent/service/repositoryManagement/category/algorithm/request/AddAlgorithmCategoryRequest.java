package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddAlgorithmCategoryRequest extends Request {

    private static final long serialVersionUID = -4672903565291369727L;

    private String name;
}
