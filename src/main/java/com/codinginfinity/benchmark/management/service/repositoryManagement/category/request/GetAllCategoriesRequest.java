package com.codinginfinity.benchmark.management.service.repositoryManagement.category.request;

import com.codinginfinity.benchmark.management.service.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by brenton on 7/25/16.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GetAllCategoriesRequest<T> extends Request
{
    private static final long serialVersionUID = 2920236744683887483L;
}
