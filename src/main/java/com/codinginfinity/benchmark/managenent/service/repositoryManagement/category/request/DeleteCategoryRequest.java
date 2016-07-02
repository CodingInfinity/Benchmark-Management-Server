package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/26.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteCategoryRequest<T extends Category> extends Request {

    private static final long serialVersionUID = 8232069500579861570L;

    private Long id;
}
