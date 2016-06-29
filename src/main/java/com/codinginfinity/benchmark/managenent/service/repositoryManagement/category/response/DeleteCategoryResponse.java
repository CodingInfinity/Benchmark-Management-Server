package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by andrew on 2016/06/26.
 */
@Getter
@AllArgsConstructor
public class DeleteCategoryResponse<T extends Category> extends Request {

    private static final long serialVersionUID = -4236881914680237709L;

    T category;
}
