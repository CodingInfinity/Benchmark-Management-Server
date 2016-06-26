package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by andrew on 2016/06/26.
 */
@Getter
@NoArgsConstructor
public class GetCategoryResponse<T extends Category> extends Request {

    private static final long serialVersionUID = 5027185410229818L;

    private T category;
}
