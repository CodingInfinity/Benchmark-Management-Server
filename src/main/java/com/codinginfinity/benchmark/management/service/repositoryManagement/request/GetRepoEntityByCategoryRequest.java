package com.codinginfinity.benchmark.management.service.repositoryManagement.request;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by reinhardt on 2016/06/27.
 */
@Getter
@AllArgsConstructor
public class GetRepoEntityByCategoryRequest <C extends Category, T extends RepoEntity<C>> extends Request {
    C category;
}
