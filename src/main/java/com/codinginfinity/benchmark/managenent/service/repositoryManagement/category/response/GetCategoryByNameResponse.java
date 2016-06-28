package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by andrew on 2016/06/28.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryByNameResponse<T extends Category> extends Response {

    private static final long serialVersionUID = 3760873141683222627L;

    private T category;
}
