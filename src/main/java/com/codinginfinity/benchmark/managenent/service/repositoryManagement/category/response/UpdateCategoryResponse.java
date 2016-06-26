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
public class UpdateCategoryResponse<T extends Category> extends Request {

    private static final long serialVersionUID = 774295711368311221L;

    private T category;
}
