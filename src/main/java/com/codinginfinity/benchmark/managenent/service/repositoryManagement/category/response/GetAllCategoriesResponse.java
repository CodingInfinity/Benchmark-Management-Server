package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * Created by brenton on 7/25/16.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCategoriesResponse<T extends Category>extends Response
{
    private static final long serialVersionUID = -3569718164314038063L;
    private List<T> categories;
}
