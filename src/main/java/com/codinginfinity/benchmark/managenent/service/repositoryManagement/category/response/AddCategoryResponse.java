package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by andrew on 2016/06/26.
 */
@Getter
@NoArgsConstructor
public class AddCategoryResponse<T extends Category> extends Request {

    private static final long serialVersionUID = 8493322010680795401L;

    private T category;
}
