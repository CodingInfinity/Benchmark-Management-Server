package com.codinginfinity.benchmark.management.service.repositoryManagement.category.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/28.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetCategoryByNameRequest<T> extends Request {

    private static final long serialVersionUID = -6824886696712083830L;

    private String name;
}
