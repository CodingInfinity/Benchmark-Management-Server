package com.codinginfinity.benchmark.management.service.repositoryManagement.category.response;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by andrew on 2016/06/28.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryByIdResponse<T extends Category> extends Response {

    private static final long serialVersionUID = -9165616883587596273L;

    private T category;
}
