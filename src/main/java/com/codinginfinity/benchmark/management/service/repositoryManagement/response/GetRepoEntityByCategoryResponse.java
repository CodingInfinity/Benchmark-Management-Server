package com.codinginfinity.benchmark.management.service.repositoryManagement.response;

import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/27.
 */
@Getter
@AllArgsConstructor
public class GetRepoEntityByCategoryResponse <T extends RepoEntity> extends Response {
    List<T> entities;
}
